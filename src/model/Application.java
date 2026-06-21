package model;

public class Application {
    private int studentId;
    private int jobId;
    private String status; // e.g., "Pending", "Accepted", "Rejected"

    public Application(int studentId,int jobId,String status){
        this.studentId=studentId;
        this.jobId=jobId;
        this.status=status;
    }

    public void displayApplication(){
        System.out.println("\n");
        System.out.println("-----Application Details-----");
        System.out.println("Student ID:"+studentId);
        System.out.println("Job ID:"+jobId);
        System.out.println("Status:"+status);
        System.out.println("-----------------------------");
    }
}
