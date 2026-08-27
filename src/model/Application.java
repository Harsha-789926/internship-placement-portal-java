package model;

public class Application {
    private int applicationId;
    private int studentId;
    private int jobId;
    private String status; // e.g., "Pending", "Accepted", "Rejected"

    public Application(int applicationId, int studentId, int jobId, String status){
        this.applicationId=applicationId;
        this.studentId=studentId;
        this.jobId=jobId;
        this.status=status;
    }

    public void displayApplication(){
        System.out.println("\n");
        System.out.println("-----Application Details-----");
        System.out.println("Application ID:"+applicationId);
        System.out.println("Student ID:"+studentId);
        System.out.println("Job ID:"+jobId);
        System.out.println("Status:"+status);
        System.out.println("-----------------------------");
    }

    public int getApplicationId(){
        return applicationId;
    }
    
    public int getStudentId(){
        return studentId;
    }

    public int getJobId(){
        return jobId;
    }

    public String getStatus(){
        return status;
    }
  
    public void setStatus(String status){
        this.status=status;
    }

}
