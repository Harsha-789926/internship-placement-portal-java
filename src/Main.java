import model.Student;
import model.Job;
import service.JobService;
import service.StudentService;
import service.RecommendationService;

public class Main {
    public static void main(String[] args) {
        Student s1=new Student("John Doe", 1, "CSE",
         8.5, "Python");

        Student s2=new Student("Jane Smith", 2, "ECE",
         9.0, "JavaScript");

        Student s3=new Student("Alex",3,"MCA",8.6,
         "Java");

        Job job1 = new Job(1, "Software Engineer", "Tech Corp",
         8.5, "Java");
        Job job2 = new Job(2, "Data Analyst", "Data Inc", 
        9.0, "SQL");

        job1.displayJob();

         StudentService service=new StudentService();
         service.addStudent(s1);
         service.addStudent(s2);
         service.addStudent(s3);
         service.viewStudents();

         JobService jobService = new JobService();
         jobService.addJob(job1);
         jobService.addJob(job2);
         jobService.viewJobs();
        
        RecommendationService recommendationService = new RecommendationService();
        recommendationService.recommendJobs(service.getStudents(), jobService.getJobs());

}
}