
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Student;
import util.DBConnection;

public class StudentService {

    // ==========================================
    // 1. ADD STUDENT
    // ==========================================

    public void addStudent(Student s) {

        String sql = "INSERT INTO STUDENT(student_id, name, email, password, cgpa, branch_id, resume_path) "
                   + "VALUES(?,?,?,?,?,?,?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getPassword());
            ps.setDouble(5, s.getCgpa());
            ps.setInt(6, s.getBranchId());
            ps.setString(7, s.getResumePath());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Failed to add student.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }


    // ==========================================
    // 2. VIEW ALL STUDENTS
    // ==========================================

    public void viewStudents() {

        String sql = "SELECT * FROM STUDENT";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                double cgpa = rs.getDouble("cgpa");
                String resumePath = rs.getString("resume_path");
                int branchId = rs.getInt("branch_id");

                System.out.println("\nStudent ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("CGPA: " + cgpa);
                System.out.println("Resume Path: " + resumePath);
                System.out.println("Branch ID: " + branchId);
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
    }


    // ==========================================
    // 3. GET STUDENT BY ID
    // ==========================================

    public Student getStudentById(int studentId) {

        String sql = "SELECT * FROM STUDENT WHERE student_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Student(
                        rs.getString("name"),
                        rs.getInt("student_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("branch_id"),
                        rs.getDouble("cgpa"),
                        rs.getString("resume_path")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving student: " + e.getMessage());
        }

        return null;
    }


    // ==========================================
    // 4. UPDATE STUDENT CGPA
    // ==========================================

    public void updateStudentCgpa(int studentId, double newCgpa) {

        if (newCgpa < 0 || newCgpa > 10 || Double.isNaN(newCgpa)) {
            System.out.println("Invalid CGPA.");
            return;
        }

        String sql = "UPDATE STUDENT SET cgpa = ? WHERE student_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setDouble(1, newCgpa);
            ps.setInt(2, studentId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student CGPA updated successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating CGPA: " + e.getMessage());
        }
    }


    // ==========================================
    // 5. UPDATE COMPLETE STUDENT PROFILE
    // ==========================================

    public void updateStudentProfile(
            int studentId,
            String name,
            String email,
            int branchId,
            double cgpa,
            String resumePath) {

        if (cgpa < 0 || cgpa > 10 || Double.isNaN(cgpa)) {
            System.out.println("Invalid CGPA.");
            return;
        }

        String sql = "UPDATE STUDENT SET name = ?, email = ?, "
                   + "branch_id = ?, cgpa = ?, resume_path = ? "
                   + "WHERE student_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, branchId);
            ps.setDouble(4, cgpa);
            ps.setString(5, resumePath);
            ps.setInt(6, studentId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student profile updated successfully!");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating student profile: " + e.getMessage());
        }
    }


    // ==========================================
    // 6. DELETE STUDENT
    // ==========================================

    public void deleteStudent(int studentId) {

        String sql = "DELETE FROM STUDENT WHERE student_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }


    // ==========================================
    // 7. STUDENT REGISTRATION
    // ==========================================

    public void registerStudent(Student student) {

        if (student == null) {
            System.out.println("Invalid student details.");
            return;
        }

        if (student.getCgpa() < 0 || student.getCgpa() > 10
                || Double.isNaN(student.getCgpa())) {
            System.out.println("Invalid CGPA.");
            return;
        }

        String sql = "SELECT student_id FROM STUDENT WHERE student_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, student.getId());

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println("Student ID already registered.");
                    return;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error checking student ID: " + e.getMessage());
            return;
        }

        addStudent(student);
    }


    // ==========================================
    // 8. STUDENT LOGIN
    // ==========================================

    public Student loginStudent(String email, String password) {

        String sql = "SELECT * FROM STUDENT WHERE email = ? AND password = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Student student = new Student(
                        rs.getString("name"),
                        rs.getInt("student_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("branch_id"),
                        rs.getDouble("cgpa"),
                        rs.getString("resume_path")
                    );

                    System.out.println("Login successful!");
                    return student;
                }
            }

            System.out.println("Invalid email or password.");

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null;
    }


    // ==========================================
    // 9. VIEW MY PROFILE
    // ==========================================

    public void viewMyProfile(Student student) {

        if (student == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.println("\n===== MY PROFILE =====");
        student.displayStudent();
    }


    // ==========================================
    // 10. UPDATE MY PROFILE
    // ==========================================

    public Student updateMyProfile(
            Student loggedInStudent,
            String name,
            String email,
            int branchId,
            double cgpa,
            String resumePath) {

        if (loggedInStudent == null) {
            System.out.println("Please login first.");
            return null;
        }

        // Get logged-in student's ID
        int studentId = loggedInStudent.getId();

        // Update database
        updateStudentProfile(
            studentId,
            name,
            email,
            branchId,
            cgpa,
            resumePath
        );

        // Retrieve latest student details
        Student updatedStudent = getStudentById(studentId);

        return updatedStudent;
    }

// 1. Update student's resume path
public void updateResume(int studentId, String resumePath) {

    String sql = "UPDATE STUDENT SET resume_path = ? WHERE student_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, resumePath);
        ps.setInt(2, studentId);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Resume path updated successfully.");
        } else {
            System.out.println("Student not found.");
        }

    } catch (SQLException e) {
        System.out.println("Error updating resume: " + e.getMessage());
    }
}

// 2. View student's resume path
public void viewResume(int studentId) {

    String sql = "SELECT resume_path FROM STUDENT WHERE student_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, studentId);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String resumePath = rs.getString("resume_path");

                if (resumePath == null || resumePath.trim().isEmpty()) {
                    System.out.println("No resume uploaded yet.");
                } else {
                    System.out.println("Resume Path: " + resumePath);
                }

            } else {
                System.out.println("Student not found.");
            }
        }

    } catch (SQLException e) {
        System.out.println("Error viewing resume: " + e.getMessage());
    }
}


}
