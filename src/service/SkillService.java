
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;

public class SkillService {

    // 1. Add a new skill to the SKILL table if it does not exist
    public void addSkill(String skillName) {

        String sql = "INSERT INTO SKILL (skill_name) VALUES (?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, skillName.trim());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Skill added successfully.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("This skill already exists.");
            } else {
                System.out.println("Error adding skill: " + e.getMessage());
            }
        }
    }

    // 2. Assign an existing skill to a student
    public void assignSkillToStudent(int studentId, int skillId) {

        String sql = "INSERT INTO STUDENT_SKILL (student_id, skill_id) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, skillId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Skill assigned to student successfully.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("This skill is already assigned to the student.");
            } else if (e.getErrorCode() == 1452) {
                System.out.println("Invalid student ID or skill ID.");
            } else {
                System.out.println("Error assigning skill: " + e.getMessage());
            }
        }
    }

    // 3. View all skills of a student
    public void viewStudentSkills(int studentId) {

        String sql = "SELECT s.skill_id, s.skill_name " +
                     "FROM SKILL s " +
                     "JOIN STUDENT_SKILL ss ON s.skill_id = ss.skill_id " +
                     "WHERE ss.student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                System.out.println("\nSkills of Student ID: " + studentId);

                while (rs.next()) {
                    found = true;

                    System.out.println(
                        rs.getInt("skill_id") + " - " +
                        rs.getString("skill_name")
                    );
                }

                if (!found) {
                    System.out.println("No skills found for this student.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error viewing skills: " + e.getMessage());
        }
    }

    // 4. Remove a skill from a student
    public void removeSkillFromStudent(int studentId, int skillId) {

        String sql = "DELETE FROM STUDENT_SKILL " +
                     "WHERE student_id = ? AND skill_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, skillId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Skill removed from student successfully.");
            } else {
                System.out.println("Skill assignment not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error removing skill: " + e.getMessage());
        }
    }

    // 5. View all available skills
    public void viewAllSkills() {

        String sql = "SELECT * FROM SKILL ORDER BY skill_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\nAvailable Skills:");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("skill_id") + " - " +
                    rs.getString("skill_name")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error viewing skills: " + e.getMessage());
        }
    }
}
