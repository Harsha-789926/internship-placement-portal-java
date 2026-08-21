import model.Student;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        int sid=4;
        boolean found=false;
        Student s1=new Student("John Doe", 1, "CSE",
         8.5, "Java,Python");

        Student s2=new Student("Jane Smith", 2, "ECE",
         9.0, "C++,JavaScript");

        Student s3=new Student("Alex",3,"MCA",8.0,
         "Java,SQL");

         ArrayList<Student> students=new ArrayList<>();
         students.add(s1);
         students.add(s2);
         students.add(s3);

         for(Student s: students){
            if(s.getId()==sid){
                s.displayStudent();
                found=true;
                break;
            }}
            if(!found){
                System.out.println("Student with id "+sid+" not found.");
            }

        
        //  for (Student s :students){
        //         s.displayStudent();
        //  }
       
    }
}
