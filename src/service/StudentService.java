package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Student;
import util.DBConnection;

public class StudentService {
    private ArrayList<Student> students;

    public StudentService(){
        students=new ArrayList<>();
        
        
    }
    
    public void addStudent(Student s) {
      try{
      Connection connection=DBConnection.getConnection();
       String sql="Insert into STUDENT(student_id,name,email,password,cgpa,branch_id,resume_path) values(?,?,?,?,?,?,?)";
       PreparedStatement ps=connection.prepareStatement(sql);
       ps.setInt(1,s.getId());
       ps.setString(2,s.getName());
        ps.setString(3,s.getEmail());
        ps.setString(4,s.getPassword());
        ps.setDouble(5,s.getCgpa());
        ps.setInt(6,s.getBranchId());
        ps.setString(7,s.getResumePath());
        int rowsAffected=ps.executeUpdate();
        if(rowsAffected>0){
            System.out.println("Student added successfully.");
            students.add(s);
        }else{
            System.out.println("Failed to add student.");
        }
    } catch(SQLException e) {
       System.out.println("Error adding student: " + e.getMessage());
      
    } 
  }

    public void viewStudents(){
     try{
      Connection connection=DBConnection.getConnection();
      String sql="Select * from student ";
      PreparedStatement ps=connection.prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      while(rs.next()){
        int id = rs.getInt("student_id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            double cgpa = rs.getDouble("cgpa");
            String resumePath = rs.getString("resume_path");
            int branchId = rs.getInt("branch_id");

            System.out.println("Student ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("CGPA: " + cgpa);
            System.out.println("Resume Path: " + resumePath);
            System.out.println("Branch ID: " + branchId);
            System.out.println("------------------------");
      }
     } catch(SQLException e) {
       System.out.println("Error viewing students: " + e.getMessage());
      }
    }


        public Student getStudentById(int studentId) {

    String sql = "SELECT * FROM STUDENT WHERE student_id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {
        ps.setInt(1, studentId);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int branchId = rs.getInt("branch_id");
                double cgpa = rs.getDouble("cgpa");
                String resumePath = rs.getString("resume_path");

                Student student = new Student(
                    name,
                    id,
                    email,
                    password,
                    branchId,
                    cgpa,
                    resumePath
                );

                return student;
            }
        }

    } catch (SQLException e) {
        System.out.println("Error retrieving student.");
        System.out.println(e.getMessage());
    }

    return null;

    }
    
public void updateStudentCgpa(int studentId, double newCgpa) {

    // Step 1: Validate CGPA
    if (newCgpa < 0 || newCgpa > 10 || Double.isNaN(newCgpa)) {
        System.out.println("Invalid CGPA");
        return;
    }

    // Step 2: SQL query
    String sql = "UPDATE STUDENT SET cgpa = ? WHERE student_id = ?";

    // Step 3: Establish connection
    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {

        // Step 4: Set parameters
        ps.setDouble(1, newCgpa);
        ps.setInt(2, studentId);

        // Step 5: Execute UPDATE
        int rowsAffected = ps.executeUpdate();

        // Step 6: Check result
        if (rowsAffected > 0) {
            System.out.println("Student updated successfully");
        } else {
            System.out.println("Student not found");
        }

    } catch (SQLException e) {
        System.out.println("Error updating student");
        System.out.println(e.getMessage());
    }
}

public void deleteStudent(int studentId) {

    String sql = "DELETE FROM STUDENT WHERE student_id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {
        ps.setInt(1, studentId);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }

    } catch (SQLException e) {
        System.out.println("Error deleting student");
        System.out.println(e.getMessage());
    }
}

    public ArrayList<Student> getStudents() {
    return students;
}
}
