//import statements
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    //class fields
    LinkedList<Student> students = new LinkedList<>();
    Scanner in = new Scanner(System.in); //one scanner object for all necessary methods

    //main method where program starts
    public static void main(String[] args) {
        System.out.println("We are going to list the students ");
        menu(0);
    }

    public static void genStudents(){


    }

    public static void showMenu(){
        System.out.println("1.) Print Student List");
        System.out.println("2.) Add a Student");
        System.out.println("3.) Remove a Student");
        System.out.println("4.) Edit Student");
        System.out.println("5.) Sort by Name");
        System.out.println("6.) Sort by Address");
        System.out.println("7.) Sort by GPA");
        System.out.println("8.) Exit");
    }

    public static void menu(int menuChoice){
        Main run = new Main(); //This will allow us to run non-static methods and call class level fields
        do{
            showMenu(); //call showMenu method
            try{
                if (run.in.hasNextInt()){
                    menuChoice = run.in.nextInt(); //set menuChoice = to the next user input int
                    switch(menuChoice){
                        case 1:
                            System.out.println("This is case 1");
                            break;
                        case 2:
                            System.out.println("This is case 2");
                            break;
                        case 3:
                            System.out.println("This is case 3");
                            break;
                        case 4:
                            System.out.println("This is case 4");
                            break;
                        case 5:
                            System.out.println("This is case 5");
                            break;
                        case 6:
                            System.out.println("This is case 6");
                            break;
                        case 7:
                            System.out.println("This is case 7");
                            break;
                        case 8:
                            System.exit(0);
                        default:
                            if (menuChoice > 8 || menuChoice < 0){
                                throw new InvalidMenuOptionException("Invalid menu Option");
                            }
                            break;
                    }

                } else {
                    throw new InputMismatchException("Not an integer.");
                }

            }catch (InputMismatchException er){
                System.out.println(er.getMessage()); //get the error message from InputMismatchException
                System.out.println("Please try again.");
                run.in.next();

            } catch (InvalidMenuOptionException menuOptionEr){
                System.out.println(menuOptionEr.getMessage());
                System.out.println("Please try again.");
            }
        } while (menuChoice != 8);

    }
}