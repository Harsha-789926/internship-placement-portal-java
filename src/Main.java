
import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

import model.Student;
import service.StudentService;
import service.AdminService;
import service.RecruiterService;
import service.JobService;
import service.ApplicationService;
import service.EducationService;
import service.SkillService;
import service.ActivityService;
import service.RecommendationService;
import util.DBConnection;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static StudentService studentService = new StudentService();
    static AdminService adminService = new AdminService();
    static RecruiterService recruiterService = new RecruiterService();
    static JobService jobService = new JobService();
    static ApplicationService applicationService = new ApplicationService();
    static EducationService educationService = new EducationService();
    static SkillService skillService = new SkillService();
    static ActivityService activityService = new ActivityService();
    static RecommendationService recommendationService = new RecommendationService();

    public static void main(String[] args) {

        try (Connection con = DBConnection.getConnection()) {
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return;
        }

        while (true) {

            System.out.println("\n================================");
            System.out.println("       CAREERBRIDGE PORTAL");
            System.out.println("================================");
            System.out.println("1. Student");
            System.out.println("2. Recruiter");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    studentEntry();
                    break;

                case 2:
                    recruiterEntry();
                    break;

                case 3:
                    adminMenu();
                    break;

                case 4:
                    System.out.println("Thank you for using CareerBridge!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // =========================================
    // STUDENT ENTRY
    // =========================================

    static void studentEntry() {

        System.out.println("\n===== STUDENT PORTAL =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Back");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            System.out.print("Enter Branch ID: ");
            int branchId = sc.nextInt();

            System.out.print("Enter CGPA: ");
            double cgpa = sc.nextDouble();
            sc.nextLine();

            System.out.print("Enter Resume Path (or leave blank): ");
            String resume = sc.nextLine();

            Student student = new Student(
                name, id, email, password, branchId, cgpa, resume
            );

            studentService.registerStudent(student);

        } else if (choice == 2) {

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            Student student = studentService.loginStudent(email, password);

            if (student != null) {
                studentMenu(student);
            }

        }
    }

    // =========================================
    // STUDENT MENU
    // =========================================

    static void studentMenu(Student student) {

        while (true) {

            System.out.println("\n========== STUDENT DASHBOARD ==========");
            System.out.println("1. View My Profile");
            System.out.println("2. Update My Profile");
            System.out.println("3. Add Education");
            System.out.println("4. View Education");
            System.out.println("5. Update Education");
            System.out.println("6. Delete Education");
            System.out.println("7. Add New Skill");
            System.out.println("8. Assign Skill to My Profile");
            System.out.println("9. View My Skills");
            System.out.println("10. Remove Skill");
            System.out.println("11. Add Activity");
            System.out.println("12. View Activities");
            System.out.println("13. Update Activity");
            System.out.println("14. Delete Activity");
            System.out.println("15. Update Resume Path");
            System.out.println("16. View Resume Path");
            System.out.println("17. View All Jobs");
            System.out.println("18. Get Recommended Jobs");
            System.out.println("19. Apply for Job");
            System.out.println("20. View My Applications");
            System.out.println("21. Logout");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    studentService.viewMyProfile(student);
                    break;

                case 2:
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Branch ID: ");
                    int branchId = sc.nextInt();

                    System.out.print("CGPA: ");
                    double cgpa = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Resume Path: ");
                    String resume = sc.nextLine();

                    student = studentService.updateMyProfile(
                        student, name, email, branchId, cgpa, resume
                    );
                    break;

                case 3:
                    System.out.print("Education Level: ");
                    String level = sc.nextLine();

                    System.out.print("Institution: ");
                    String institution = sc.nextLine();

                    System.out.print("Percentage: ");
                    double percentage = sc.nextDouble();

                    System.out.print("CGPA: ");
                    double eduCgpa = sc.nextDouble();

                    System.out.print("Passing Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();

                    educationService.addEducation(
                        student.getId(), level, institution,
                        percentage, eduCgpa, year
                    );
                    break;

                case 4:
                    educationService.viewEducation(student.getId());
                    break;

                case 5:
                    System.out.print("Education ID: ");
                    int educationId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Education Level: ");
                    level = sc.nextLine();

                    System.out.print("Institution: ");
                    institution = sc.nextLine();

                    System.out.print("Percentage: ");
                    percentage = sc.nextDouble();

                    System.out.print("CGPA: ");
                    eduCgpa = sc.nextDouble();

                    System.out.print("Passing Year: ");
                    year = sc.nextInt();
                    sc.nextLine();

                    educationService.updateEducation(
                        educationId, level, institution,
                        percentage, eduCgpa, year
                    );
                    break;

                case 6:
                    System.out.print("Education ID: ");
                    educationService.deleteEducation(sc.nextInt());
                    sc.nextLine();
                    break;

                case 7:
                    System.out.print("Enter Skill Name: ");
                    skillService.addSkill(sc.nextLine());
                    break;

                case 8:
                    skillService.viewAllSkills();

                    System.out.print("Enter Skill ID: ");
                    int skillId = sc.nextInt();
                    sc.nextLine();

                    skillService.assignSkillToStudent(student.getId(), skillId);
                    break;

                case 9:
                    skillService.viewStudentSkills(student.getId());
                    break;

                case 10:
                    System.out.print("Enter Skill ID to remove: ");
                    skillService.removeSkillFromStudent(
                        student.getId(), sc.nextInt()
                    );
                    sc.nextLine();
                    break;

                case 11:
                    System.out.print("Activity Type: ");
                    String type = sc.nextLine();

                    System.out.print("Activity Name: ");
                    String activityName = sc.nextLine();

                    System.out.print("Role: ");
                    String role = sc.nextLine();

                    System.out.print("Description: ");
                    String description = sc.nextLine();

                    System.out.print("Achievement: ");
                    String achievement = sc.nextLine();

                    activityService.addActivity(
                        student.getId(), type, activityName,
                        role, description, achievement
                    );
                    break;

                case 12:
                    activityService.viewActivities(student.getId());
                    break;

                case 13:
                    System.out.print("Activity ID: ");
                    int activityId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Activity Type: ");
                    type = sc.nextLine();

                    System.out.print("Activity Name: ");
                    activityName = sc.nextLine();

                    System.out.print("Role: ");
                    role = sc.nextLine();

                    System.out.print("Description: ");
                    description = sc.nextLine();

                    System.out.print("Achievement: ");
                    achievement = sc.nextLine();

                    activityService.updateActivity(
                        activityId, type, activityName,
                        role, description, achievement
                    );
                    break;

                case 14:
                    System.out.print("Activity ID: ");
                    activityService.deleteActivity(sc.nextInt());
                    sc.nextLine();
                    break;

                case 15:
                    System.out.print("Enter Resume Path: ");
                    studentService.updateResume(
                        student.getId(), sc.nextLine()
                    );
                    break;

                case 16:
                    studentService.viewResume(student.getId());
                    break;

                case 17:
                    jobService.viewJobs();
                    break;

                case 18:
                    recommendationService.recommendJobs(student.getId());
                    break;

                case 19:
                    System.out.print("Enter Job ID: ");
                    int jobId = sc.nextInt();
                    sc.nextLine();

                    applicationService.applyForJob(student.getId(), jobId);
                    break;

                case 20:
                    applicationService.viewStudentApplications(student.getId());
                    break;

                case 21:
                    System.out.println("Student logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // =========================================
    // RECRUITER ENTRY
    // =========================================

    static void recruiterEntry() {

        System.out.println("\n===== RECRUITER PORTAL =====");
        System.out.println("1. Register Company");
        System.out.println("2. Login");
        System.out.println("3. Back");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {

            System.out.print("Company ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Company Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            System.out.print("Contact Number: ");
            String contact = sc.nextLine();

            System.out.print("Location: ");
            String location = sc.nextLine();

            recruiterService.registerRecruiter(
                id, name, email, password, contact, location
            );

        } else if (choice == 2) {

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            int companyId = recruiterService.loginRecruiter(email, password);

            if (companyId != -1) {
                recruiterMenu(companyId);
            }
        }
    }

    // =========================================
    // RECRUITER MENU
    // =========================================

    static void recruiterMenu(int companyId) {

        while (true) {

            System.out.println("\n========== RECRUITER DASHBOARD ==========");
            System.out.println("1. View Company Profile");
            System.out.println("2. View My Jobs");
            System.out.println("3. Post Job");
            System.out.println("4. Update Job");
            System.out.println("5. Delete Job");
            System.out.println("6. View Applicants");
            System.out.println("7. Update Applicant Status");
            System.out.println("8. Logout");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    recruiterService.viewCompanyProfile(companyId);
                    break;

                case 2:
                    recruiterService.viewCompanyJobs(companyId);
                    break;

                case 3:
                    System.out.print("Job ID: ");
                    int jobId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Job Title: ");
                    String title = sc.nextLine();

                    System.out.print("Description: ");
                    String description = sc.nextLine();

                    System.out.print("Package: ");
                    String packageDetails = sc.nextLine();

                    System.out.print("Location: ");
                    String location = sc.nextLine();

                    System.out.print("Minimum CGPA: ");
                    double cgpa = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Deadline (yyyy-mm-dd): ");
                    Date deadline = Date.valueOf(sc.nextLine());

                    recruiterService.addRecruiterJob(
                        companyId, jobId, title, description,
                        packageDetails, location, cgpa, deadline
                    );
                    break;

                case 4:
                    System.out.print("Job ID: ");
                    jobId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Description: ");
                    description = sc.nextLine();

                    System.out.print("Package: ");
                    packageDetails = sc.nextLine();

                    System.out.print("Location: ");
                    location = sc.nextLine();

                    System.out.print("Minimum CGPA: ");
                    cgpa = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Deadline (yyyy-mm-dd): ");
                    deadline = Date.valueOf(sc.nextLine());

                    recruiterService.updateRecruiterJob(
                        companyId, jobId, description,
                        packageDetails, location, cgpa, deadline
                    );
                    break;

                case 5:
                    System.out.print("Job ID to delete: ");
                    jobId = sc.nextInt();
                    sc.nextLine();

                    recruiterService.deleteRecruiterJob(companyId, jobId);
                    break;

                case 6:
                    recruiterService.viewApplicants(companyId);
                    break;

                case 7:
                    System.out.print("Application ID: ");
                    int applicationId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Status (Shortlisted/Rejected/Selected): ");
                    String status = sc.nextLine();

                    recruiterService.updateApplicantStatus(
                        companyId, applicationId, status
                    );
                    break;

                case 8:
                    System.out.println("Recruiter logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // =========================================
    // ADMIN MENU
    // =========================================

    static void adminMenu() {

        while (true) {

            System.out.println("\n========== ADMIN DASHBOARD ==========");
            System.out.println("1. View Companies");
            System.out.println("2. Verify Company");
            System.out.println("3. Reject Company");
            System.out.println("4. View Students");
            System.out.println("5. View Student");
            System.out.println("6. Update Student CGPA");
            System.out.println("7. Delete Student");
            System.out.println("8. View Jobs");
            System.out.println("9. Delete Job");
            System.out.println("10. View Applications");
            System.out.println("11. View Application");
            System.out.println("12. Update Application Status");
            System.out.println("13. Logout");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    adminService.viewCompanies();
                    break;

                case 2:
                    System.out.print("Company ID: ");
                    adminService.verifyCompany(sc.nextInt());
                    sc.nextLine();
                    break;

                case 3:
                    System.out.print("Company ID: ");
                    adminService.rejectCompany(sc.nextInt());
                    sc.nextLine();
                    break;

                case 4:
                    adminService.viewAllStudents();
                    break;

                case 5:
                    System.out.print("Student ID: ");
                    adminService.viewStudent(sc.nextInt());
                    sc.nextLine();
                    break;

                case 6:
                    System.out.print("Student ID: ");
                    int studentId = sc.nextInt();

                    System.out.print("New CGPA: ");
                    double cgpa = sc.nextDouble();
                    sc.nextLine();

                    adminService.updateStudentCgpa(studentId, cgpa);
                    break;

                case 7:
                    System.out.print("Student ID: ");
                    adminService.deleteStudent(sc.nextInt());
                    sc.nextLine();
                    break;

                case 8:
                    adminService.viewAllJobs();
                    break;

                case 9:
                    System.out.print("Job ID: ");
                    adminService.deleteJob(sc.nextInt());
                    sc.nextLine();
                    break;

                case 10:
                    adminService.viewAllApplications();
                    break;

                case 11:
                    System.out.print("Application ID: ");
                    adminService.viewApplication(sc.nextInt());
                    sc.nextLine();
                    break;

                case 12:
                    System.out.print("Application ID: ");
                    int applicationId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Status: ");
                    String status = sc.nextLine();

                    adminService.updateApplicationStatus(applicationId, status);
                    break;

                case 13:
                    System.out.println("Admin menu closed.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
