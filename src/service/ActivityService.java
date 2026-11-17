
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;

public class ActivityService {

    // 1. Add a student activity
    public void addActivity(int studentId, String activityType,
                            String activityName, String role,
                            String description, String achievement) {

        String sql = "INSERT INTO STUDENT_ACTIVITY " +
                "(student_id, activity_type, activity_name, role, description, achievement) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, activityType);
            ps.setString(3, activityName);
            ps.setString(4, role);
            ps.setString(5, description);
            ps.setString(6, achievement);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Activity added successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding activity: " + e.getMessage());
        }
    }

    // 2. View activities of a student
    public void viewActivities(int studentId) {

        String sql = "SELECT * FROM STUDENT_ACTIVITY WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println("\nActivity ID: " + rs.getInt("activity_id"));
                    System.out.println("Activity Type: " + rs.getString("activity_type"));
                    System.out.println("Activity Name: " + rs.getString("activity_name"));
                    System.out.println("Role: " + rs.getString("role"));
                    System.out.println("Description: " + rs.getString("description"));
                    System.out.println("Achievement: " + rs.getString("achievement"));
                    System.out.println("-----------------------------");
                }

                if (!found) {
                    System.out.println("No activities found for this student.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error viewing activities: " + e.getMessage());
        }
    }

    // 3. Update an activity
    public void updateActivity(int activityId, String activityType,
                               String activityName, String role,
                               String description, String achievement) {

        String sql = "UPDATE STUDENT_ACTIVITY SET activity_type = ?, " +
                "activity_name = ?, role = ?, description = ?, achievement = ? " +
                "WHERE activity_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, activityType);
            ps.setString(2, activityName);
            ps.setString(3, role);
            ps.setString(4, description);
            ps.setString(5, achievement);
            ps.setInt(6, activityId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Activity updated successfully.");
            } else {
                System.out.println("Activity not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating activity: " + e.getMessage());
        }
    }

    // 4. Delete an activity
    public void deleteActivity(int activityId) {

        String sql = "DELETE FROM STUDENT_ACTIVITY WHERE activity_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, activityId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Activity deleted successfully.");
            } else {
                System.out.println("Activity not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting activity: " + e.getMessage());
        }
    }
}
