import model.Student;
// import model.Job;
// import model.Application;
// import model.Company;   
// import model.Admin;
// import service.JobService;
import service.StudentService;
// import service.RecommendationService;
// import service.ApplicationService;
// import service.CompanyService;
// import service.AdminService;

// import util.DBConnection;
// import java.sql.Connection;
// import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Student s1=new Student("John Doe", 1, "CSE",
        //  8.5, "Python");

        // Student s2=new Student("Jane Smith", 2, "ECE",
        //  9.0, "JavaScript");

        // Student s3=new Student("Alex",3,"MCA",8.6,
        //  "Java");

//          Student student = new Student(
//     "Rahul",
//     3,
//     "rahul1@gmail.com",
//     "test123",
//     1,
//     8.4,
//     "resume/rahul.pdf"
// );

        // Job job1 = new Job(1, "Software Engineer", "Tech Corp",
        //  8.5, "Java");
        // Job job2 = new Job(2, "Data Analyst", "Data Inc", 
        // 9.0, "SQL");

        

         StudentService service=new StudentService();
        //  service.addStudent(student);
         service.viewStudents();

        //  JobService jobService = new JobService();
        //  jobService.addJob(job1);
        //  jobService.addJob(job2);
        //  jobService.viewJobs();

        
        
        // RecommendationService recommendationService = new RecommendationService();
        // recommendationService.recommendJobs(service.getStudents(), jobService.getJobs());
        
        // ApplicationService applicationService = new ApplicationService();
        // applicationService.applyForJob(1, s1.getId(), job1.getId());
        // applicationService.viewApplications();
        // applicationService.updateStatus(1, "Shortlisted");
        // applicationService.viewApplications();

//         Application foundApplication = applicationService.getApplicationById(1);
//         if(foundApplication!=null){
//             foundApplication.displayApplication();      
//         }else{
            
//             System.out.println("Application not found.");
//         }
    
//         Company c1 = new Company(1, "Tech Corp", "hr@techcorp.com", "Mysore");   

//         CompanyService companyService = new CompanyService();
//         companyService.addCompany(c1);
//         companyService.viewCompanies();

//         Company foundCompany = companyService.getCompanyById(1);

//         if(foundCompany!=null){
//             foundCompany.displayCompany();
//         }else{
//             System.out.println("Company not found.");
//         }

//          Admin admin=new Admin(1, "Placement Officer", "Placement");

//          AdminService adminService=new AdminService();
//          adminService.addAdmin(admin);
//          adminService.viewAdmin();
         

//          Admin foundAdmin=adminService.getAdminById(1);
//          if(foundAdmin!=null){
//             foundAdmin.displayAdmin();
//          }else{
            
//             System.out.println("Admin not found.");
//          }

//          adminService.updateApplicationStatus(applicationService, 1, "Selected");
//          applicationService.viewApplications();

//          companyService.updateApplicationStatus(applicationService, 1, "shortlisted");
//          applicationService.viewApplications();

//          try {
//             Connection connection=DBConnection.getConnection();
//             if(connection!=null){
//                 System.out.println("Database connection established successfully.");
//             }
//          } catch (Exception e) {
//             System.out.println("Error occurred while establishing database connection.");
//             System.out.println(e.getMessage() );
//          }
 } 
 }
