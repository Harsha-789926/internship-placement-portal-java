import java.sql.Connection;
import util.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBTest {

    public static void main(String[] args) {

        try {
            Connection connection = DBConnection.getConnection();
            String sql="Select student_id,name,cgpa from STUDENT";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               int id=rs.getInt("student_id");
               String name=rs.getString("name");
               double cgpa=rs.getDouble("cgpa");
               System.out.println("ID: "+id+" Name: "+name+" CGPA: "+cgpa);
            }

            String sql1="Delete from STUDENT where student_id=?";
            PreparedStatement ps1=connection.prepareStatement(sql1);
            ps1.setInt(1,3 );
            int rowsAffected=ps1.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Student deleted successfully.");
            }
    //         String sql1 =
    // "INSERT INTO STUDENT " +
    // "(student_id, name, email, password, cgpa, branch_id) " +
    // "VALUES (?, ?, ?, ?, ?, ?)";
    //   PreparedStatement ps1 = connection.prepareStatement(sql1);
    //         ps1.setInt(1, 3);
    //         ps1.setString(2, "John");
    //         ps1.setString(3, "john@gmail.com");
    //         ps1.setString(4, "test123");
    //         ps1.setDouble(5, 8.2);
    //         ps1.setInt(6, 1);
            
//             String sql2="Update STUDENT set cgpa=? WHERE student_id=?";
//             PreparedStatement ps2=connection.prepareStatement(sql2);
//             ps2.setDouble(1, 9.0);
//             ps2.setInt(2,1 );
//             int rowsAffected1=ps2.executeUpdate();
//             if (rowsAffected1 > 0) {
//     System.out.println("Student CGPA updated successfully.");
// } else {
//     System.out.println("Student not found.");
// }
            // int rowsAffected = ps1.executeUpdate();
            // if(rowsAffected > 0) {
            //     System.out.println("Data inserted successfully.");
            // } else {
            //     System.out.println("No data was inserted.");
            // }
            if (connection != null) {
                System.out.println("Database connection established successfully.");
            }

        } catch (Exception e) {
            System.out.println("Error occurred while establishing database connection.");
            System.out.println(e.getMessage());
        }
    }
}