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

    public void printStudents(){ //used for case 1 in menu()
        try{
                if (students.size() == 0){
                    throw new InvalidAmountException("There are no students to list! ");
                }
                else{
                    for (int i = 0; i < students.size(); i++){
                        System.out.println("Student Number: " + (i + 1) + ", " + students.get(i));
                        System.out.println();
                    }
                }


        }catch (InvalidAmountException invalidAmountException){
            System.out.println(invalidAmountException.getMessage());
        }


    }

    public void addStudent(){ //used for case 2 in menu()
        System.out.println("How many students would you like to add?");
        int numToAdd;
        numToAdd = in.nextInt();
        System.out.println("Adding " + numToAdd + " students");
        for(int i = 0; i < numToAdd; i++){
            Student student = new Student();
            System.out.print("Name: ");
            student.setName(in.next());
            in.nextLine();

            System.out.print("Address: ");
            student.setAddress(in.nextLine());

            System.out.print("GPA: ");
            student.setGPA(in.nextDouble());
            students.add(student);
        }

    }

    public void removeStudent() { //used for case 3 in menu()
        System.out.println("Which student would you like to remove?");
        students.remove(in.nextInt() - 1);
        System.out.println("Student removed.");
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
                        case 1: // case for printing out the list of students
                            run.printStudents();
                            break;
                        case 2: //case for adding a student to the list
                            run.addStudent();
                            break;
                        case 3: //case for removing a student from the list
                            run.removeStudent();
                            break;
                        case 4:
                            //TODO
                            break;
                        case 5:
                            //TODO
                            break;
                        case 6:
                            //TODO
                            break;
                        case 7:
                            //TODO
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