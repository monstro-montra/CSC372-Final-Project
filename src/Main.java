//import statements

import java.util.*;

public class Main {
    //class fields
    LinkedList<Student> students = new LinkedList<>();
    StudentNameCompare nameCompare = new StudentNameCompare();
    StudentAddressCompare addressCompare = new StudentAddressCompare();
    StudentGPACompare gpaCompare = new StudentGPACompare();
    static Scanner in = new Scanner(System.in); //one scanner object for all necessary methods

    //main method where program starts
    public static void main(String[] args) {
        Main run = new Main(); //This will allow us to run non-static methods and call class level fields
        run.menu(0);
    }

    public boolean isPopulated() {
        try{
            if (students.size() == 0){ // if the size of students is 0
                throw new InvalidAmountException("There are no students in the list!"); //throw InvalidAmountException
            } else {
                return true; //otherwise, return true
            }
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage()); //get message from InvalidAmountException
            return false; //method returns false
        }

    }

    public void printStudents(){ //used for case 1 in menu()
        if (isPopulated()) { //call isPopulated method
            for (int i = 0; i < students.size(); i++) { // keep looping until i = students.size()
                // print all students in format specified in overridden Student class toString() method
                System.out.println("Student Number " + (i + 1) + ": " + students.get(i));
            }
        }
    }

    public void addStudent(){ //used for case 2 in menu()
        String studentName;
        String studentAddress;
        double studentGPA;

        int numToAdd = howManyStudents(); //call howManyStudents() method
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
        int numToAdd; //variable used for user input
        System.out.println("How many students would you like to add?");
        while(true){ //start while loop
            try {
                System.out.print ("Number of students: ");
                numToAdd = in.nextInt(); //set the number of students to add = to userInput
                if (numToAdd < 1  ) { //if the numToAdd is negative or 0
                    throw new InvalidAmountException("Invalid amount."); //throw InvalidAmountException
                }
                return numToAdd;
            } catch (InputMismatchException er){ //catch InputMismatchException
                System.out.println("Not an integer. Please try again");
                in.next();
            } catch (InvalidAmountException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static String userInputName(){ //will be used in addStudent() and editStudent()
        //name user input
        String studentName; //user input will be held in this variable
        while(true) { //start a while loop
            try {
                System.out.print("Name: ");
                studentName = in.nextLine();
                String[] subArray = studentName.split(" "); //split the studentName subArray by " " (white space)
                for (String s : subArray) { //for each
                    if (validateString(s)) { //run validateString on every name the user uses
                        return studentName; //return the studentName
                    } else {
                        throw new InputMismatchException("Incorrect Input. Must contain letters only."); //throw InputMismatchException
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
            if(string.matches("[A-Za-z]*")){ //if the string is alphabetical
                return true; //return true
            } else { //otherwise
                throw new InputMismatchException(); //throw InputMismatchException
            }
        } catch (InputMismatchException er){
            return false; //return false
        }
    }

    public static String userInputAddress(){ //will be used in addStudent() and editStudent()
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

    public static double userInputGPA(){ //will be used in addStudent() and editStudent()
        //GPA user input
        double studentGPA; //user input will be held in this variable
        while(true) { //start while loop
            try{ //start try-catch
                System.out.print("GPA: ");
                if (in.hasNextDouble()){
                    studentGPA = in.nextDouble(); //set studentInput to next double value
                    if(studentGPA > 0 && studentGPA <= 4.0){ // if student GPA > 0 and <= 4.0
                        in.nextLine(); //use up extra \n
                        break; //break from loop
                    } else { //otherwise throw exception
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

    public void removeStudent()  { //used for case 3 in menu()
            while(isPopulated()){ //start loop while students is Populated
                printStudents();
                try {
                    System.out.println("Which student would you like to remove? You may cancel with the letter 'C' or 'c'.");
                    if (in.hasNextInt()) {
                        students.remove(in.nextInt() - 1);
                        System.out.println("Student removed.");
                        break;
                    } else if(isCancel()){ //if the user wrote "C" or "c"
                        return; //leave from method
                    } else {
                        throw new InputMismatchException("Not an integer.");
                    }
                } catch (InputMismatchException er){
                    System.out.println(er.getMessage());
                    System.out.println("Please try again");
                    in.next(); //prevents infinite loop
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Out of bounds!");
                    System.out.println("Please try again.");
                }

            } //end while loop
    }

    public void editStudent(){
        int studentChoice;

            while(isPopulated()){ //check if isPopulated returns true
                printStudents(); //run printStudents
                try {
                    System.out.println("Which student would you like to edit? You may cancel with the letter 'C' or 'c'.");

                    if (in.hasNextInt()) { //if next input is Int
                        studentChoice = in.nextInt() - 1; // - 1 to keep up with consistency of user readability
                        in.nextLine(); //skips extra \n
                        students.get(studentChoice).setName(userInputName()); //get the position of studentChoice in students, then setName
                        students.get(studentChoice).setAddress(userInputAddress());//get the position of studentChoice in students, then setAddress
                        students.get(studentChoice).setGPA(userInputGPA());//get the position of studentChoice in students, then setGPA
                        System.out.println("Student edited");
                        break;
                    } else if (isCancel()){
                        return;
                    }else {
                        throw new InputMismatchException("Not an integer.");
                    }
                } catch (InputMismatchException er){
                    System.out.println(er.getMessage());
                    System.out.println("Please try again");
                    in.next(); //prevents infinite loop
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Out of bounds!");
                    System.out.println("Please try again.");
                }

            } //end while loop

        }

    public void sort(LinkedList<Student> list, Comparator<Student> comparator){ //custom sort method to sort by name, address, or GPA
        //selection sort algorithm
        for (int i = 0; i < list.size() - 1; i ++){
            int minIndex = i; // minIndex == to current iteration of loop
            for (int j = i + 1; j < students.size(); j++){
                if(comparator.compare(students.get(j), students.get(minIndex)) < 0){ //check if element in question (j) is smaller than current minimum
                    minIndex = j; // update minIndex to be value in question
                }
            }
            swap(list, i, minIndex);
        }
    }

    public void swap(LinkedList<Student> list, int i1, int i2){
        Student temp = list.get(i1); //temp = list i1
        list.set(i1, list.get(i2)); //set i1 to i2
        list.set(i2, temp);
    }

    public static boolean isCancel(){ //this method allows user to cancel out of edit and remove method
        if (in.hasNext("C") || in.hasNext("c")){ //if user input is "C" or "c"
            System.out.println("Cancelling. Returning to main menu.");
            in.next();
            return true;
        }
        return false;
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

    public void menu(int menuChoice){
        do{
            showMenu(); //call showMenu method
            try{
                if (in.hasNextInt()){
                    menuChoice = in.nextInt(); //set menuChoice = to the next user input int
                    switch (menuChoice) { //enhanced switch removes need for break statements.
                        case 1 -> // case for printing out the list of students
                                printStudents();
                        case 2 -> //case for adding a student to the list
                                addStudent();
                        case 3 -> //case for removing a student from the list
                                removeStudent();
                        case 4 -> //case for editing a student
                                editStudent();
                        case 5 -> { //case for sorting by name
                            sort(students, nameCompare);
                            System.out.println("Sorted by Name!");
                        }
                        case 6 -> { //case for sorting by address
                            sort(students, addressCompare);
                            System.out.println("Sorted by address!");
                        }
                        case 7 -> { //case for sorting by GPA
                            sort(students, gpaCompare);
                            System.out.println("Sorted by GPA!");
                        }
                        case 8 ->  //case for exiting program
                                System.exit(0);
                        default -> throw new InvalidMenuOptionException("Invalid menu Option");
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
        } while (menuChoice != 8); //as long as the menuChoice isn't 8 or program is closed, continue looping.
    }
}