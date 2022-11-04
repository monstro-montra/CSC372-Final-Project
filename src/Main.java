//import statements
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    //class fields
    LinkedList<Student> students = new LinkedList<>();
    static Scanner in = new Scanner(System.in); //one scanner object for all necessary methods

    //main method where program starts
    public static void main(String[] args) {
        menu(0);
    }


    public void printStudents(){ //used for case 1 in menu()
        try{
            if (students.size() == 0){
                throw new InvalidAmountException("There are no students to list! ");
            }
            else{
                for (int i = 0; i < students.size(); i++){
                    System.out.println("Student Number " + (i + 1) + ": " + students.get(i));
                    System.out.println();
                }
            }
        } catch (InvalidAmountException invalidAmountException){
            System.out.println(invalidAmountException.getMessage());
        }
    }

    public void addStudent(){ //used for case 2 in menu()
        System.out.println("How many students would you like to add?");
        int numToAdd;
        String studentName;
        String studentAddress;
        double studentGPA;
        numToAdd = in.nextInt();
        System.out.println("Adding " + numToAdd + " student(s)");
        for(int i = 0; i < numToAdd; i++){
            Student student = new Student(); //create a new while loop during each iteration
            System.out.println("Student Number: " + (i+1)); // (i+1) because lists start at index 0. readability

            studentName = userInputName(); //call userInputName() method
            student.setName(studentName);

            studentAddress = userInputAddress(); //call userInputAddress() method
            student.setAddress(studentAddress);

            studentGPA = userInputGPA(); //call userInputGPA() method
            student.setGPA(studentGPA);

            students.add(student);
        }

    }

    public static String userInputName(){ //will be used in addStudent()
        //name user input
        String studentName; //user input will be held in this variable
        while(true) { //start a while loop
            try {
                System.out.print("Name: ");
                if (in.hasNext("[A-Za-z]*")) { //if in next input is alphabetical
                    studentName = in.next(); //setName of student object to user input token (without any white spaces)
                    in.nextLine(); // necessary to populate the next line. readability purposes
                    break; // break out of the while loop
                } else { //otherwise
                    throw new InputMismatchException("Input must contain letters only."); //throw an InputMismatchException
                }
            } catch (InputMismatchException er) { //catch InputMismatchException
                System.out.println(er.getMessage()); //get message of InputMismatchException
                System.out.println("Please try again"); //prompt user to try again
                in.next(); //will prevent an infinite loop
            } //end catch
        } //end loop
        return studentName;
    }

    public static String userInputAddress(){ //will be used in addStudent()
        //address user input
        String studentAddress; //user input will be held in this variable
        while (true) { //start while loop
            try{
                System.out.print("Address: ");
                studentAddress = in.nextLine();
                if (studentAddress.matches("[A-Za-z]*")){ // if next input is alphabetical and has numbers
                    break; //break out of loop
                } else if (studentAddress.matches("[A-Za-z]*" + "[123456789]")){ // or if next input is just alphabetical
                    break; //break out of loop
                } else if(studentAddress.matches("[123456789]" +"[A-Za-z]*" +  ".")){ // or if next input is alphabetical, has ints, and a period
                    break; //break out of loop
                } else {
                    throw new InputMismatchException("Incorrect input. Must be an address."); //throw InputMismatchException
                }
            } catch (InputMismatchException er){ //catch InputMismatchException
                System.out.println(er.getMessage()); //get message of InputMismatchException
                System.out.println("Please try again.");
                in.skip(""); //necessary to prevent infinite loop
            } //end catch
        } //end while loop
        return studentAddress;
    }

    public static double userInputGPA(){ //will be used in addStudent()
        //GPA user input
        double studentGPA; //user input will be held in this variable
        while(true) { //start while loop
            try{ //start try-catch
                System.out.print("GPA: ");
                if (in.hasNextDouble()){
                    studentGPA = in.nextDouble();
                    if(studentGPA > 0 && studentGPA < 4.0){
                        break;
                    } else {
                        throw new InvalidAmountException("GPA value is out of bounds. Must be between 0 and 4.");
                    }

                } else {
                    throw new InputMismatchException("User input was not a GPA value.");
                }

            } catch (InvalidAmountException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again");
            } catch (InputMismatchException er){
                System.out.println(er.getMessage());
                System.out.println("Please try again.");
                in.next(); //will prevent an infinite loop
            } // end catch clauses
        } //end while loop
        return studentGPA;
    }

    public void removeStudent() { //used for case 3 in menu()
        System.out.println("Which student would you like to remove?");
        students.remove(in.nextInt() - 1);
        System.out.println("Student removed.");
    }
    public static void showMenu(){
        System.out.println();
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
                if (in.hasNextInt()){
                    menuChoice = in.nextInt(); //set menuChoice = to the next user input int
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
                in.next();

            } catch (InvalidMenuOptionException menuOptionEr){
                System.out.println(menuOptionEr.getMessage());
                System.out.println("Please try again.");
            }
        } while (menuChoice != 8);

    }
}