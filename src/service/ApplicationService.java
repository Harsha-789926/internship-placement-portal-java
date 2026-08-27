package service;
import model.Application;
import java.util.ArrayList;

public class ApplicationService {
   private ArrayList<Application> applications;

   public ApplicationService(){
    applications=new ArrayList<>();
   }

   public void addApplication(Application a){
    applications.add(a);
   }

   public void viewApplications(){
    for(Application a : applications){
        a.displayApplication();
    }
   }

   public void updateStatus(int applicationId,String newStatus){
      for(Application a:applications){
         if(a.getApplicationId()==applicationId){
            a.setStatus(newStatus);
            break;
         }
      }
   }

   public void applyForJob(int applicationId,int studentId,int jobId){
      Application existing=searchApplication(studentId, jobId);
      if(existing==null){
         Application application=new Application(applicationId,studentId,jobId,"Applied");
         applications.add(application);
      }else{
         System.out.println("Application already exists.");
      }
   }

   public Application searchApplication(int studentId,int jobId){
   for(Application a:applications){
      if(a.getStudentId()==studentId && a.getJobId()==jobId){
         return a;
      }
   }
   return null;
}

 
   public Application getApplicationById(int applicationId) {
  for(Application a:applications){
   if(a.getApplicationId()==applicationId){
      return a;
   }
  }
  return null;
}
}