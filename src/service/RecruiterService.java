
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBConnection;

public class RecruiterService {

    // 1. Recruiter Login
    public int loginRecruiter(String email, String password) {

        String sql = "SELECT company_id FROM COMPANY " +
                     "WHERE TRIM(email) = TRIM(?) " +
                     "AND password = ? " +
                     "AND TRIM(verification_status) = 'Verified'";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            email = email.trim();
            password = password.trim();

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println("Recruiter login successful!");
                    return rs.getInt("company_id");
                } else {
                    System.out.println("Invalid email or password, or company not verified.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return -1;
    }

    // 2. Register Recruiter
    public void registerRecruiter(int companyId, String companyName,
                                  String email, String password,
                                  String contactNo, String location) {

        String sql = "INSERT INTO COMPANY " +
                     "(company_id, company_name, email, password, contact_no, location) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, companyId);
            ps.setString(2, companyName);
            ps.setString(3, email.trim());
            ps.setString(4, password);
            ps.setString(5, contactNo);
            ps.setString(6, location);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Recruiter registered successfully!");
                System.out.println("Your account is awaiting Admin verification.");
            }

        } catch (SQLException e) {
            System.out.println("Error registering recruiter: " + e.getMessage());
        }
    }

    // 3. View Company Profile
    public void viewCompanyProfile(int companyId) {

        String sql = "SELECT * FROM COMPANY WHERE company_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, companyId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println("\n===== COMPANY PROFILE =====");
                    System.out.println("Company ID: " + rs.getInt("company_id"));
                    System.out.println("Company Name: " + rs.getString("company_name"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Contact: " + rs.getString("contact_no"));
                    System.out.println("Location: " + rs.getString("location"));
                    System.out.println("===========================");
                } else {
                    System.out.println("Company profile not found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error viewing company profile: " + e.getMessage());
        }
    }

    // 4. View Company's Jobs
    public void viewCompanyJobs(int companyId) {

        String sql = "SELECT job_id, title, description, package, " +
                     "location, minimum_cgpa, deadline " +
                     "FROM JOB WHERE company_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, companyId);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println("\n----- JOB DETAILS -----");
                    System.out.println("Job ID: " + rs.getInt("job_id"));
                    System.out.println("Title: " + rs.getString("title"));
                    System.out.println("Description: " + rs.getString("description"));
                    System.out.println("Package: " + rs.getString("package"));
                    System.out.println("Location: " + rs.getString("location"));
                    System.out.println("Minimum CGPA: " + rs.getDouble("minimum_cgpa"));
                    System.out.println("Deadline: " + rs.getDate("deadline"));
                }

                if (!found) {
                    System.out.println("No jobs found for this company.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error viewing company jobs: " + e.getMessage());
        }
    }

    // 5. Add Recruiter Job
    public void addRecruiterJob(int companyId, int jobId, String title,
                                String description, String packageDetails,
                                String location, double minimumCgpa,
                                java.sql.Date deadline) {

        String checkSql = "SELECT verification_status FROM COMPANY WHERE company_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement checkPs = con.prepareStatement(checkSql)
        ) {
            checkPs.setInt(1, companyId);

            try (ResultSet rs = checkPs.executeQuery()) {

                if (!rs.next() ||
                    !"Verified".equalsIgnoreCase(rs.getString("verification_status"))) {

                    System.out.println("Only verified companies can post jobs.");
                    return;
                }
            }

            String insertSql = "INSERT INTO JOB " +
                "(job_id, company_id, title, description, package, location, minimum_cgpa, deadline) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(insertSql)) {

                ps.setInt(1, jobId);
                ps.setInt(2, companyId);
                ps.setString(3, title);
                ps.setString(4, description);
                ps.setString(5, packageDetails);
                ps.setString(6, location);
                ps.setDouble(7, minimumCgpa);
                ps.setDate(8, deadline);

                ps.executeUpdate();

                System.out.println("Job posted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error posting job: " + e.getMessage());
        }
    }

    // 6. Update Recruiter Job
    public void updateRecruiterJob(int companyId, int jobId,
                                   String description, String packageDetails,
                                   String location, double minimumCgpa,
                                   java.sql.Date deadline) {

        String sql = "UPDATE JOB SET description = ?, package = ?, " +
                     "location = ?, minimum_cgpa = ?, deadline = ? " +
                     "WHERE job_id = ? AND company_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, description);
            ps.setString(2, packageDetails);
            ps.setString(3, location);
            ps.setDouble(4, minimumCgpa);
            ps.setDate(5, deadline);
            ps.setInt(6, jobId);
            ps.setInt(7, companyId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Job updated successfully.");
            } else {
                System.out.println("Job not found or unauthorized access.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating job: " + e.getMessage());
        }
    }

    // 7. Delete Recruiter Job
    public void deleteRecruiterJob(int companyId, int jobId) {

        String sql = "DELETE FROM JOB WHERE job_id = ? AND company_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, jobId);
            ps.setInt(2, companyId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Job deleted successfully.");
            } else {
                System.out.println("Job not found or unauthorized access.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting job: " + e.getMessage());
        }
    }

    // 8. View Applicants
    public void viewApplicants(int companyId) {

        String sql = "SELECT a.application_id, s.name, s.email, " +
                     "j.title, s.cgpa, a.status, a.applied_date " +
                     "FROM APPLICATION a " +
                     "JOIN STUDENT s ON a.student_id = s.student_id " +
                     "JOIN JOB j ON a.job_id = j.job_id " +
                     "WHERE j.company_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, companyId);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println("\n===== APPLICANT DETAILS =====");
                    System.out.println("Application ID: " + rs.getInt("application_id"));
                    System.out.println("Student Name: " + rs.getString("name"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Job Title: " + rs.getString("title"));
                    System.out.println("CGPA: " + rs.getDouble("cgpa"));
                    System.out.println("Status: " + rs.getString("status"));
                    System.out.println("Applied Date: " + rs.getDate("applied_date"));
                    System.out.println("=============================");
                }

                if (!found) {
                    System.out.println("No applicants found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error viewing applicants: " + e.getMessage());
        }
    }

    // 9. Update Applicant Status
    private final ApplicationService applicationService = new ApplicationService();

    public void updateApplicantStatus(int companyId, int applicationId, String status) {

        String sql = "SELECT a.application_id " +
                     "FROM APPLICATION a " +
                     "JOIN JOB j ON a.job_id = j.job_id " +
                     "WHERE a.application_id = ? AND j.company_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, applicationId);
            ps.setInt(2, companyId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    if (!status.equalsIgnoreCase("Shortlisted") &&
                        !status.equalsIgnoreCase("Rejected") &&
                        !status.equalsIgnoreCase("Selected")) {

                        System.out.println("Invalid application status.");
                        return;
                    }

                    applicationService.updateStatus(applicationId, status);

                } else {
                    System.out.println(
                        "Unauthorized: This application does not belong to your company."
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error updating applicant status: " + e.getMessage());
        }
    }
}
