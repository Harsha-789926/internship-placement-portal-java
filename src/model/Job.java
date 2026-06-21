package model;

public class Job {
    private int id;
    private String title;
    private String company;
    private String skillsrequired;

    public Job(int id,String title,String company,String skillsrequired){
        this.id=id;
        this.title=title;
        this.company=company;
        this.skillsrequired=skillsrequired;
    }
    public void displayJob(){
        System.out.println("\n");
        System.out.println("-----Job Details-----");
        System.out.println("Job Id:"+id);
        System.out.println("Title:"+title);
        System.out.println("Company:"+company);
        System.out.println("Skills Required:"+skillsrequired);
        System.out.println("-----------------------------");
    }
}
