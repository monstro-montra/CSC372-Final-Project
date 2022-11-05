import java.util.Comparator;

//compare by name
class StudentNameCompare implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2 ){
        return s1.getName().compareTo(s2.getName());
    }
}

//compare by address
class StudentAddressCompare implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2 ){
        return s1.getAddress().compareTo(s2.getAddress());
    }
}

//compare by GPA
class StudentGPACompare implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2 ){
        // if s1 GPA is == s2 GPA
        // if s1 GPA is < s2 GPA
        //if s1 GPA is > than s2 GPA
        return Double.compare(s1.getGPA(), s2.getGPA());
    }
}
