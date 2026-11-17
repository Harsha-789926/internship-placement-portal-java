package service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBConnection;


public class RecommendationService {

    public void recommendJobs(int studentId) {
String sql = "SELECT " +
        "st.student_id, st.name, st.cgpa, " +
        "j.job_id, j.title, c.company_name, j.minimum_cgpa " +
        "FROM STUDENT st " +
        "JOIN JOB j ON st.cgpa >= j.minimum_cgpa " +
        "JOIN COMPANY c ON j.company_id = c.company_id " +
        "WHERE st.student_id = ? " +
        "AND EXISTS ( " +
        "SELECT 1 FROM JOB_BRANCH jb " +
        "WHERE jb.job_id = j.job_id " +
        "AND jb.branch_id = st.branch_id " +
        ") " +
        "AND ( " +
        "SELECT COUNT(*) FROM JOB_SKILL js " +
        "WHERE js.job_id = j.job_id " +
        ") = ( " +
        "SELECT COUNT(*) " +
        "FROM JOB_SKILL js " +
        "JOIN STUDENT_SKILL ss ON ss.skill_id = js.skill_id " +
        "WHERE js.job_id = j.job_id " +
        "AND ss.student_id = st.student_id " +
        ")";try (
    Connection connection = DBConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(sql);

) {
    ps.setInt(1, studentId);
    ResultSet rs = ps.executeQuery();
    boolean found = false;
    while (rs.next()) {
    found = true;
    int jobId = rs.getInt("job_id");
    String title = rs.getString("title");
    String company = rs.getString("company_name");
    double minimumCgpa = rs.getDouble("minimum_cgpa");

    System.out.println("\n---------------------------");
    System.out.println("Job ID: " + jobId);
    System.out.println("Job Title: " + title);
    System.out.println("Company: " + company);
    System.out.println("Minimum CGPA: " + minimumCgpa);
}
if (!found) {
    System.out.println("No eligible jobs found for this student.");
}
} catch (SQLException e) {
    System.out.println("Error recommending jobs: " + e.getMessage());
}
}
    // public void recommendJobs( ArrayList<Student> students, ArrayList<Job> jobs ) {
    // for (Student s : students) {
    // for (Job j : jobs) {
    //     if(s.getCgpa()>=j.getMinimumCgpa()&& s.getSkills().equalsIgnoreCase(j.getRequiredSkills())){

    //         System.out.println("\n---------------------------");
    //         System.out.println(s.getName()+" : "+s.getCgpa()+" :Eligible for "+
    //         j.getTitle()+" at "+j.getCompany());
    //     }else{
    //         System.out.println("\n---------------------------");
    //         System.out.println(s.getName()+": "+s.getCgpa()+" Not eligible  for "
    //             +j.getTitle()+" at "+j.getCompany());
    //     }
    // }
// }
// }

}
