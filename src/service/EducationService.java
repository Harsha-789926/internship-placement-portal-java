
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;

public class EducationService {

    // 1. Add education details
    public void addEducation(int studentId, String educationLevel,
                             String institution, double percentage,
                             double cgpa, int passingYear) {

        String sql = "INSERT INTO EDUCATION " +
                "(student_id, education_level, institution, percentage, cgpa, passing_year) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, educationLevel);
            ps.setString(3, institution);
            ps.setDouble(4, percentage);
            ps.setDouble(5, cgpa);
            ps.setInt(6, passingYear);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Education details added successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding education details: " + e.getMessage());
        }
    }

    // 2. View education details by student ID
    public void viewEducation(int studentId) {

        String sql = "SELECT * FROM EDUCATION WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println("\nEducation ID: " + rs.getInt("education_id"));
                    System.out.println("Education Level: " + rs.getString("education_level"));
                    System.out.println("Institution: " + rs.getString("institution"));
                    System.out.println("Percentage: " + rs.getDouble("percentage"));
                    System.out.println("CGPA: " + rs.getDouble("cgpa"));
                    System.out.println("Passing Year: " + rs.getInt("passing_year"));
                    System.out.println("-----------------------------");
                }

                if (!found) {
                    System.out.println("No education details found for this student.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error viewing education details: " + e.getMessage());
        }
    }

    // 3. Update education details
    public void updateEducation(int educationId, String educationLevel,
                                String institution, double percentage,
                                double cgpa, int passingYear) {

        String sql = "UPDATE EDUCATION SET education_level = ?, institution = ?, " +
                "percentage = ?, cgpa = ?, passing_year = ? WHERE education_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, educationLevel);
            ps.setString(2, institution);
            ps.setDouble(3, percentage);
            ps.setDouble(4, cgpa);
            ps.setInt(5, passingYear);
            ps.setInt(6, educationId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Education details updated successfully.");
            } else {
                System.out.println("Education record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating education details: " + e.getMessage());
        }
    }

    // 4. Delete education details
    public void deleteEducation(int educationId) {

        String sql = "DELETE FROM EDUCATION WHERE education_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, educationId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Education details deleted successfully.");
            } else {
                System.out.println("Education record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting education details: " + e.getMessage());
        }
    }
}
