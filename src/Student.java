public class Student {
    //class fields
    private String name;
    private String address;
    private double GPA;

    //main constructor
    public Student(String studentName, String studentAddress, double studentGPA){
        name = studentName;
        address = studentAddress;
        GPA = studentGPA;
    }

    //constructor to be used with setters
    public Student(){

    }


    //getters
    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public double getGPA(){
        return this.GPA;
    }

    //setters (fields will not be final)
    public void setName(String newName){
        this.name = newName;
    }

    public void setAddress(String newAddress){
        this.address = newAddress;
    }

    public void setGPA(double newGPA){
        this.GPA = newGPA;
    }

    @Override
    public String toString(){ //custom version of toString method to be used int printStudents method in main
        return  "Name: " + name + ", " +
                "Address: " + address + ", " +
                "GPA: " + GPA;
    }
}
