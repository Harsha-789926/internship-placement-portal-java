package service;

import java.util.ArrayList;
import model.Student;

public class StudentService {
    private ArrayList<Student> students;

    public StudentService(){
        students=new ArrayList<>();
    }
    
    public void addStudent(Student s) {
    students.add(s);
    }  

    public void viewStudents(){
      for(Student s:students){
        s.displayStudent();
      }
    }
    public ArrayList<Student> getStudents() {
    return students;
}
}
