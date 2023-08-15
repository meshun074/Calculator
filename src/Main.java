import Calculator.Calculate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        do {
            System.out.print("Hello and welcome to MKNE Calculator!\n");
            //accepts input as user equation and calls the calculator class
            Calculate.calculator(UserInput.getEquation());
            System.out.println("Enter 1 to continue or any key to exit");
        }
        while (UserInput.getOption()==1);
    }
}