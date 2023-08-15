package Calculator;

import java.util.ArrayList;
import java.util.List;

public class Operators {
    public static String power(String equation)
    {
        // calculate exponential terms by splitting equation using the exponential symbol
        List<String> terms =new ArrayList<>(List.of(equation.split("\\^")));
        //convert each term to double and reduce by performing maths power function on pair terms
        double mathsTerms= terms.stream().map(Double::parseDouble).toList().stream().reduce(Math::pow).orElse(1.0);
        return String.valueOf(mathsTerms);
    }
    public static String multiply(String equation)
    {
//       calculate multiplicatiion equation
        List<String> terms =new ArrayList<>(List.of(equation.split("\\*")));
        double mathsTerms= terms.stream().map(Double::parseDouble).toList().stream().reduce(1.0, (x,y)->x*y);
        return String.valueOf(mathsTerms);
    }
    public static String divide(String equation)
    {
//        calculate division
        List<String> terms =new ArrayList<>(List.of(equation.split("/")));
        double mathsTerms= terms.stream().map(Double::parseDouble).toList().stream().reduce((x,y)->x/y).orElse(0.0);
        return String.valueOf(mathsTerms);
    }
    public static String add(String equation)
    {
//        calculate addition
        List<String> terms =new ArrayList<>(List.of(equation.split("\\+")));
        double mathsTerms= terms.stream().map(Double::parseDouble).toList().stream().reduce(0.0, Double::sum);
        return String.valueOf(mathsTerms);
    }
    public static String subtract(String equation)
    {
//        calculate subtraction
        List<String> terms;
//        differentiate between negative terms and the minus symbol
        if(equation.indexOf("-")==0)
        {
            terms =new ArrayList<>(List.of(equation.substring(1).split("-")));
            terms.set(0,"-"+terms.get(0));
        }else {
            terms = new ArrayList<>(List.of(equation.split("-")));
        }
        double mathsTerms= terms.stream().map(Double::parseDouble).toList().stream().reduce((x, y)->x-y).orElse(0.0);
        return String.valueOf(mathsTerms);
    }

}
