package Calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculate {
    //solve the equation
    public static void calculator(String equation){
        System.out.println(getSolution(equation));
    }

    //return each character in the equation
    private static List<String> equationList(String equation)
    {
        return new ArrayList<>(List.of(equation.split("")));
    }

    //check for numeric values
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

//    solve equation using BODMAS
    private static String solveEquation(String equation)
    {
        if(isNumeric(equation))
            return equation;
        String extEquation;
        List<String> terms;
        while(equation.contains("^")) {
           //exponential
            terms =new ArrayList<>(List.of(equation.split("\\^")));
            if(terms.stream().allMatch(Calculate::isNumeric))
               equation=Operators.power(equation);
            else {
                extEquation= getTermedEquation(equation,"^");
                equation= updateEquation(equation,extEquation,Operators.power(extEquation));
            }
        }

        while (equation.contains("/")) {
            //division
            terms =new ArrayList<>(List.of(equation.split("/")));
            if(terms.stream().allMatch(Calculate::isNumeric))
                equation=Operators.divide(equation);
            else {
                extEquation= getTermedEquation(equation,"/");
                equation= updateEquation(equation,extEquation,Operators.divide(extEquation));
            }
        }

        while (equation.contains("*"))
        {
            //multiplication
            terms =new ArrayList<>(List.of(equation.split("\\*")));
            if(terms.stream().allMatch(Calculate::isNumeric))
                equation=Operators.multiply(equation);
            else {
                extEquation= getTermedEquation(equation,"*");
                equation= updateEquation(equation,extEquation,Operators.multiply(extEquation));
            }
        }

        while(equation.contains("-")&& !isNumeric(equation) && equation.substring(1).contains("-")) {
            //subtraction
            if(equation.indexOf("-")==0)
            {
                terms =new ArrayList<>(List.of(equation.substring(1).split("-")));
            }else {
                terms = new ArrayList<>(List.of(equation.split("-")));
            }
            if(terms.stream().allMatch(Calculate::isNumeric)) {
                equation = Operators.subtract(equation);
            }
            else {
                extEquation= getTermedEquation(equation,"-");
                equation= updateEquation(equation,extEquation,Operators.subtract(extEquation));
            }
        }

        while (equation.contains("+")) {
            //addition
            terms =new ArrayList<>(List.of(equation.split("\\+")));
            if(terms.stream().allMatch(Calculate::isNumeric))
                equation=Operators.add(equation);
            else {
                extEquation= getTermedEquation(equation,"+");
                equation= updateEquation(equation,extEquation,Operators.add(extEquation));
            }
        }

        return equation;
    }

//    return a term for example operand/operator/operand
    private static String getTermedEquation(String equation, String operator)
    {
        StringBuilder term1;
        term1 = new StringBuilder();
        StringBuilder term2;
        term2 = new StringBuilder();
        boolean term1add = true;
        boolean term2add = true;
//        x keeps track of first term while y keeps track of second term
        int x_start = equation.indexOf(operator)!=0 ?equation.indexOf(operator) - 1:equation.substring(1).indexOf(operator);
        int y_start = equation.indexOf(operator)!=0? equation.indexOf(operator) + 1 : equation.substring(1).indexOf(operator) + 2;
        List<String> equationCharList = equationList(equation);
        for (int x = x_start, y = y_start; x>-1 || y<equationCharList.size(); x--, y++) {
            try {
                if (isNumeric(equationCharList.get(x)) && term1add || equationCharList.get(x).equals(".") && term1add || equationCharList.get(x).equals("-") && x==x_start && term1add)
                    term1.append(equationCharList.get(x));
                else if (term1add) {
                    term1add = false;
                }
            }catch (IndexOutOfBoundsException e)
            {
                term1add = false;
            }

            try{
                if (isNumeric(equationCharList.get(y)) && term2add || equationCharList.get(y).equals(".")&& term2add || equationCharList.get(y).equals("-") && y==y_start && term2add)
                    term2.append(equationCharList.get(y));
                else
                    term2add = false;
            }catch (IndexOutOfBoundsException e)
            {
                term2add=false;
            }
            if (!term1add && !term2add)
                break;
        }
        return term1.reverse()+operator+term2;
    }

//    calculate equation in brackets before solving equations
    private static double getSolution(String equation){
        String subequation;
        while (equation.contains("("))
        {
            subequation=getBracketEquation(equation);
            equation=updateEquation(equation,"("+subequation+")",solveEquation(subequation));
        }
        return Double.parseDouble(solveEquation(equation));
    }

    //Replaces equation with results after it has been solved
    private static String updateEquation(String equation, String subEquation, String result)
    {
        String newEquation;
        newEquation=equation.replace(subEquation,result).replaceAll("\\+-","-").replaceAll("-\\+","-");
        return newEquation;
    }

//    get an equation in bracket at a time
    private static String getBracketEquation(String equation){
        String subEquation;
        if(equation.contains("(")&&equation.contains(")")) {
            subEquation=equation.substring(equation.indexOf("(")+1,equation.indexOf(")"));
            if(subEquation.contains("(")|| subEquation.contains(")")) {
                return getBracketEquation(subEquation);
            }
            else {
                return subEquation;
            }
        }else if(equation.contains("("))
        {
            subEquation=equation.substring(equation.lastIndexOf("(")+1);
            if(subEquation.contains("(")|| subEquation.contains(")")) {
                return getBracketEquation(subEquation);
            }
            else {
                return subEquation;
            }
        } else if (equation.contains(")")) {
            return "";
        }
        return equation;
    }
}
