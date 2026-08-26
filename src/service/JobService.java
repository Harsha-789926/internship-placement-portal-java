package service;

import model.Job;
import java.util.ArrayList;

public class JobService {
    private ArrayList<Job> jobs;
     
    public JobService(){
        jobs=new ArrayList<>();
    }

     public void addJob(Job j) {  //to add new jobs
        jobs.add(j);
    } 

    public void viewJobs(){
        for(Job j:jobs){
            j.displayJob();
        }
    }
   

    public ArrayList<Job> getJobs(){
        return jobs;
    }
}
