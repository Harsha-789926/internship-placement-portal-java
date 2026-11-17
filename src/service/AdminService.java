
package service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Application;
import model.Student;
import util.DBConnection;

public class AdminService {
    private JobService jobService = new JobService();
    private StudentService studentService = new StudentService();
    private ApplicationService applicationService = new ApplicationService();

    public void viewCompanies() {

        String sql = "SELECT company_id, company_name, email, contact_no, location, verification_status FROM COMPANY";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== REGISTERED COMPANIES =====");

            while (rs.next()) {

                System.out.println("Company ID: " + rs.getInt("company_id"));
                System.out.println("Company Name: " + rs.getString("company_name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Contact: " + rs.getString("contact_no"));
                System.out.println("Location: " + rs.getString("location"));
                System.out.println("Verification Status: " + rs.getString("verification_status"));

                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            System.out.println("Error viewing companies: " + e.getMessage());
        }
    }


public void verifyCompany(int companyId) {

    String sql = "UPDATE COMPANY SET verification_status = 'Verified' " +
                 "WHERE company_id = ? AND verification_status = 'Pending'";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setInt(1, companyId);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Company verified successfully!");
        } else {
            System.out.println("Company not found or already processed.");
        }

    } catch (Exception e) {
        System.out.println("Error verifying company: " + e.getMessage());
    }
}


public void rejectCompany(int companyId) {

    String sql = "UPDATE COMPANY SET verification_status = 'Rejected' " +
                 "WHERE company_id = ? AND verification_status = 'Pending'";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setInt(1, companyId);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Company rejected successfully!");
        } else {
            System.out.println("Company not found or already processed.");
        }

    } catch (Exception e) {
        System.out.println("Error rejecting company: " + e.getMessage());
    }
}

public void viewAllStudents() {
    System.out.println("\n===== ALL STUDENTS =====");
    studentService.viewStudents();
}

public void viewStudent(int studentId) {

    Student student = studentService.getStudentById(studentId);

    if (student != null) {
        student.displayStudent();
    } else {
        System.out.println("Student not found.");
    }
}

public void updateStudentCgpa(int studentId, double cgpa) {
    studentService.updateStudentCgpa(studentId, cgpa);
}

public void deleteStudent(int studentId) {
    studentService.deleteStudent(studentId);
}
public void viewAllJobs() {
    System.out.println("\n===== ALL JOBS =====");
    jobService.viewJobs();
}

public void deleteJob(int jobId) {
    jobService.deleteJob(jobId);
}
public void viewAllApplications() {
    System.out.println("\n===== ALL STUDENT APPLICATIONS =====");
    applicationService.viewApplications();
}

public void viewApplication(int applicationId) {

    Application application =
            applicationService.getApplicationById(applicationId);

    if (application != null) {
        application.displayApplication();
    } else {
        System.out.println("Application not found.");
    }
}
public void updateApplicationStatus(int applicationId, String status) {
    applicationService.updateStatus(applicationId, status);
}
public void updateStudentProfile(
        int studentId,
        String name,
        String email,
        int branchId,
        double cgpa,
        String resumePath) {

    studentService.updateStudentProfile(
        studentId, name, email, branchId, cgpa, resumePath
    );
}

}
