
package service;

import model.Application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBConnection;

public class ApplicationService {

    // Apply for a job with eligibility validation
    public void applyForJob(int studentId, int jobId) {

        String eligibilitySql =
            "SELECT j.job_id " +
            "FROM STUDENT s " +
            "JOIN JOB j ON j.job_id = ? " +
            "WHERE s.student_id = ? " +
            "AND s.cgpa >= j.minimum_cgpa " +

            // Check whether student's branch is eligible
            "AND EXISTS (" +
                "SELECT 1 FROM JOB_BRANCH jb " +
                "WHERE jb.job_id = j.job_id " +
                "AND jb.branch_id = s.branch_id" +
            ") " +

            // Check whether student has all required skills
            "AND NOT EXISTS (" +
                "SELECT 1 FROM JOB_SKILL js " +
                "LEFT JOIN STUDENT_SKILL ss " +
                "ON ss.skill_id = js.skill_id " +
                "AND ss.student_id = s.student_id " +
                "WHERE js.job_id = j.job_id " +
                "AND ss.skill_id IS NULL" +
            ")";

        String checkSql =
            "SELECT application_id FROM APPLICATION " +
            "WHERE student_id = ? AND job_id = ?";

        String insertSql =
            "INSERT INTO APPLICATION " +
            "(student_id, job_id, status, applied_date) " +
            "VALUES (?, ?, 'Applied', CURRENT_DATE)";

        try (Connection connection = DBConnection.getConnection()) {

            // Step 1: Check eligibility
            try (PreparedStatement eligibilityPs =
                     connection.prepareStatement(eligibilitySql)) {

                eligibilityPs.setInt(1, jobId);
                eligibilityPs.setInt(2, studentId);

                try (ResultSet rs = eligibilityPs.executeQuery()) {

                    if (!rs.next()) {
                        System.out.println(
                            "You are not eligible for this job."
                        );
                        System.out.println(
                            "Check your CGPA, branch and required skills."
                        );
                        return;
                    }
                }
            }

            // Step 2: Check duplicate application
            try (PreparedStatement checkPs =
                     connection.prepareStatement(checkSql)) {

                checkPs.setInt(1, studentId);
                checkPs.setInt(2, jobId);

                try (ResultSet rs = checkPs.executeQuery()) {

                    if (rs.next()) {
                        System.out.println(
                            "Application already exists."
                        );
                        return;
                    }
                }
            }

            // Step 3: Submit application
            try (PreparedStatement insertPs =
                     connection.prepareStatement(insertSql)) {

                insertPs.setInt(1, studentId);
                insertPs.setInt(2, jobId);

                int rows = insertPs.executeUpdate();

                if (rows > 0) {
                    System.out.println(
                        "Application submitted successfully!"
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Error applying for job: " + e.getMessage()
            );
        }
    }

    // View all applications
    public void viewApplications() {

        String sql = "SELECT * FROM APPLICATION";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                Application application = new Application(
                    rs.getInt("application_id"),
                    rs.getInt("student_id"),
                    rs.getInt("job_id"),
                    rs.getString("status")
                );

                application.displayApplication();
            }

            if (!found) {
                System.out.println("No applications found.");
            }

        } catch (SQLException e) {
            System.out.println(
                "Error viewing applications: " + e.getMessage()
            );
        }
    }

    // Search application using student ID and job ID
    public Application searchApplication(int studentId, int jobId) {

        String sql =
            "SELECT * FROM APPLICATION " +
            "WHERE student_id = ? AND job_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);
            ps.setInt(2, jobId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Application(
                        rs.getInt("application_id"),
                        rs.getInt("student_id"),
                        rs.getInt("job_id"),
                        rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Error searching application: " + e.getMessage()
            );
        }

        return null;
    }

    // Update application status
    public void updateStatus(int applicationId, String newStatus) {

        String sql =
            "UPDATE APPLICATION SET status = ? " +
            "WHERE application_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, newStatus);
            ps.setInt(2, applicationId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                    "Application status updated successfully!"
                );
            } else {
                System.out.println("Application not found.");
            }

        } catch (SQLException e) {
            System.out.println(
                "Error updating status: " + e.getMessage()
            );
        }
    }

    // Get application using application ID
    public Application getApplicationById(int applicationId) {

        String sql =
            "SELECT * FROM APPLICATION WHERE application_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, applicationId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Application(
                        rs.getInt("application_id"),
                        rs.getInt("student_id"),
                        rs.getInt("job_id"),
                        rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Error retrieving application: " + e.getMessage()
            );
        }

        return null;
    }

    // View application history of a student
    public void viewStudentApplications(int studentId) {

        String sql =
            "SELECT a.application_id, a.job_id, j.title, " +
            "c.company_name, a.status, a.applied_date " +
            "FROM APPLICATION a " +
            "JOIN JOB j ON a.job_id = j.job_id " +
            "JOIN COMPANY c ON j.company_id = c.company_id " +
            "WHERE a.student_id = ? " +
            "ORDER BY a.applied_date DESC";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println(
                        "\nApplication ID: " +
                        rs.getInt("application_id")
                    );

                    System.out.println(
                        "Job ID: " + rs.getInt("job_id")
                    );

                    System.out.println(
                        "Job Title: " + rs.getString("title")
                    );

                    System.out.println(
                        "Company: " + rs.getString("company_name")
                    );

                    System.out.println(
                        "Status: " + rs.getString("status")
                    );

                    System.out.println(
                        "Applied Date: " + rs.getDate("applied_date")
                    );

                    System.out.println("-----------------------------");
                }

                if (!found) {
                    System.out.println(
                        "You have not applied for any jobs yet."
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Error viewing application history: " +
                e.getMessage()
            );
        }
    }
}
