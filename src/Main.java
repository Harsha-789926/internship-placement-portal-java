import model.Student;
import model.Job;
import java.util.ArrayList;
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

         ArrayList<Student> students=new ArrayList<>();
         students.add(s1);
         students.add(s2);
         students.add(s3);

         ArrayList<Job> jobs=new ArrayList<>();
         jobs.add(job1);
         jobs.add(job2);

        //  for(Student s: students){
        //     if(s.getId()==sid){
        //         s.displayStudent();
        //         found=true;
        //         break;
        //     }}
        //     if(!found){
        //         System.out.println("Student with id "+sid+" not found.");
        //     }

        
         for (Student s :students){
            for(Job j:jobs){
            if(s.getCgpa()>=j.getMinimumCgpa() && s.getSkills().contains(j.getRequiredSkills())){ 
                System.out.println("\n---------------------------");
                System.out.println(s.getName()+" : "+s.getCgpa()+" :Eligible for "+
                j.getTitle()+" at "+j.getCompany());  
                
                // s.displayStudent();
         }else{
        System.out.println("\n---------------------------");
        System.out.println(s.getName()+": "+s.getCgpa()+" Not eligible  for "
        +j.getTitle()+" at "+j.getCompany());
        }
    }
    }
}
}