//import statements

import java.util.*;

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
                }
            }
        } catch (InvalidAmountException invalidAmountException){
            System.out.println(invalidAmountException.getMessage());
        }
    }

    public void addStudent(){ //used for case 2 in menu()
        String studentName;
        String studentAddress;
        double studentGPA;

        int numToAdd = howManyStudents();

        in.nextLine(); //will consume the empty \n
        System.out.println("Adding " + numToAdd + " student(s)");
        for(int i = 0; i < numToAdd; i++){
            Student student = new Student(); //create a new while loop during each iteration
            System.out.println("Student Number: " + (i+1)); // (i+1) because lists start at index 0. readability

            studentName = userInputName(); //call userInputName() method
            student.setName(studentName); //set the name of the student object to user input

            studentAddress = userInputAddress(); //call userInputAddress() method
            student.setAddress(studentAddress); //set the address of the student object to user input

            studentGPA = userInputGPA(); //call userInputGPA() method
            student.setGPA(studentGPA); //set the GPA of the student object to user input

            students.add(student); //add student object to the students linked list
        }

    }

    public static int howManyStudents(){ //will be used in addStudent()
        int numToAdd;
        System.out.println("How many students would you like to add?");
        while(true){
            try {
                System.out.print ("Number of students: ");
                numToAdd = in.nextInt();
                return numToAdd;
            } catch (InputMismatchException er){
                System.out.println("Not an integer. Please try again");
                in.next();
            }

        }
    }

    public static String userInputName(){ //will be used in addStudent()
        //name user input
        String studentName; //user input will be held in this variable
        while(true) { //start a while loop
            try {
                System.out.print("Name: ");
                studentName = in.nextLine();
                String[] subArray = studentName.split(" "); //split the studentName subArray by " " (white space)
                for (String s : subArray) {
                    if (validateString(s)) {
                        return studentName;
                    } else {
                        throw new InputMismatchException("Incorrect Input. Must contain letters only.");
                    }
                }
            } catch (InputMismatchException er) { //catch InputMismatchException
                System.out.println(er.getMessage()); //get message of InputMismatchException
                System.out.println("Please try again"); //prompt user to try again
                in.skip("");
            } //end catch
        } //end loop
    }

    public static boolean validateString(String string){ //will be used in the userInputName() method
        try{
            if(string.matches("[A-Za-z]*")){
                return true;
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException er){
            return false;
        }
    }

    public static String userInputAddress(){ //will be used in addStudent()
        //address user input
        String studentAddress; //user input will be held in this variable
        while (true) { //start while loop
            try{
                System.out.print("Address: ");
                studentAddress = in.nextLine(); //format for address should be similar to this: 504 Windmill St.
                String[] subArray = studentAddress.split(" "); //split the address by " " (whitespace) into an array
                if (isStringInt(subArray[0]) && subArray[1].matches("[A-Za-z]*") && subArray[2].contains(".")){
                    break;
                } else if (isStringInt(subArray[0]) && subArray[1].matches("[A-Za-z]*") && subArray[2].matches("[A-Za-z]*")){
                    break;
                } else {
                    throw new InvalidAddressFormatException("Invalid Address format. ");
                }
            } catch (InvalidAddressFormatException er){ //catch InvalidAddressFormatException
                System.out.println(er.getMessage()); //get message of InvalidAddressFormatException
                System.out.println("Please format similar to the following example: 201 Bloomingdale Rd.");
                in.skip(""); //necessary to prevent infinite loop
            } catch (IndexOutOfBoundsException er) {
                System.out.println("Address not provided. Please format similar to the following example: 201 Bloomingdale Rd.");
                in.skip("");
            }//end catch
        } //end while loop
        return studentAddress;
    }

    public static boolean isStringInt(String string){ // this will check if a string is an integer. To use in userInputAddress method
        try{
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException er){
            return false;
        }
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