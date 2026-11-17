package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBConnection;

import model.Job;


public class JobService {

public void addJob(int jobId, int companyId, String title,
                   String description, String packageDetails,
                   String location, double minimumCgpa,
                   java.sql.Date deadline) {

    String sql = "INSERT INTO JOB " +
                 "(job_id, company_id, title, description, package, location, minimum_cgpa, deadline) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {
        ps.setInt(1, jobId);
        ps.setInt(2, companyId);
        ps.setString(3, title);
        ps.setString(4, description);
        ps.setString(5, packageDetails);
        ps.setString(6, location);
        ps.setDouble(7, minimumCgpa);
        ps.setDate(8, deadline);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Job added successfully!");
        }

    } catch (SQLException e) {
        System.out.println("Error adding job: " + e.getMessage());
    }
}

public Job getJobById(int jobId) {

    String sql = "SELECT j.job_id, j.title, c.company_name, j.minimum_cgpa " +
                 "FROM JOB j " +
                 "INNER JOIN COMPANY c ON j.company_id = c.company_id " +
                 "WHERE j.job_id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {
        ps.setInt(1, jobId);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int id = rs.getInt("job_id");
                String title = rs.getString("title");
                String company = rs.getString("company_name");
                double minimumCgpa = rs.getDouble("minimum_cgpa");

                return new Job(id, title, company, minimumCgpa, "");
            }
        }

    } catch (SQLException e) {
        System.out.println("Error retrieving job: " + e.getMessage());
    }

    return null;
}

public void updateJob(int jobId, String description,
                      String packageDetails, String location,
                      double minimumCgpa, java.sql.Date deadline) {

    String sql = "UPDATE JOB SET description = ?, package = ?, " +
                 "location = ?, minimum_cgpa = ?, deadline = ? " +
                 "WHERE job_id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {
        ps.setString(1, description);
        ps.setString(2, packageDetails);
        ps.setString(3, location);
        ps.setDouble(4, minimumCgpa);
        ps.setDate(5, deadline);
        ps.setInt(6, jobId);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Job updated successfully!");
        } else {
            System.out.println("Job not found.");
        }

    } catch (SQLException e) {
        System.out.println("Error updating job: " + e.getMessage());
    }
}

public void deleteJob(int jobId) {

    String sql = "DELETE FROM JOB WHERE job_id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {
        ps.setInt(1, jobId);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Job deleted successfully!");
        } else {
            System.out.println("Job not found.");
        }

    } catch (SQLException e) {
        System.out.println("Error deleting job: " + e.getMessage());
    }
}

     public void viewJobs() {  //to view jobs
      String sql = "SELECT j.job_id, j.title, c.company_name, j.minimum_cgpa " +
             "FROM JOB j " +
             "INNER JOIN COMPANY c ON j.company_id = c.company_id";
       try(
        Connection connection=DBConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
       )
    {
       while(rs.next()){
            int id = rs.getInt("job_id");
            String title = rs.getString("title");
            String company = rs.getString("company_name");
            double minimumCgpa = rs.getDouble("minimum_cgpa");

            Job job=new Job(id,title,company,minimumCgpa,"");
            job.displayJob();
        }
       } catch (Exception e) {
         System.out.println("Error viewing job: " + e.getMessage());
       }
    }
    }
