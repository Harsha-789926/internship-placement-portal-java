package service;
import model.Student;
import model.Job;
import java.util.ArrayList;



public class RecommendationService {
    public void recommendJobs( ArrayList<Student> students, ArrayList<Job> jobs ) {
    for (Student s : students) {
    for (Job j : jobs) {
        if(s.getCgpa()>=j.getMinimumCgpa()&& s.getSkills().equalsIgnoreCase(j.getRequiredSkills())){
               
            System.out.println("\n---------------------------");
            System.out.println(s.getName()+" : "+s.getCgpa()+" :Eligible for "+
            j.getTitle()+" at "+j.getCompany());
        }else{
            System.out.println("\n---------------------------");
            System.out.println(s.getName()+": "+s.getCgpa()+" Not eligible  for "
                +j.getTitle()+" at "+j.getCompany());
        }
    }
}
}

}
