import java.util.Scanner;

public class UserInput {
    private  static Scanner sc;
//    get users input equation
    public static String getEquation(){
        sc = new Scanner(System.in);
        System.out.println("Enter your equation for calculation eg. 34*4/5+43");
        String equation;
        equation = sc.nextLine();
        return equation;
    }
    // get user option to continue or exit
    public static int getOption(){
        int option;
        try {
            option=Integer.parseInt(sc.nextLine());
            return option;
        }catch (NumberFormatException e)
        {
            return 0;
        }
    }
}
