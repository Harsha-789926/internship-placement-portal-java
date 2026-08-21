package model;

public class Job {
    private int id;
    private String title;
    private String company;
    private double minimumCgpa;
    private String requiredSkills;

    public Job(int id,String title,String company,double minimumCgpa,String requiredSkills){
        this.id=id;
        this.title=title;
        this.company=company;
        this.minimumCgpa=minimumCgpa;
        this.requiredSkills=requiredSkills;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getCompany() {
        return company;
    }
    public double getMinimumCgpa() {
        return minimumCgpa;
    }
    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void displayJob(){
        System.out.println("-----Job Details-----");
        System.out.println("Job Id:"+id);
        System.out.println("Title:"+title);
        System.out.println("Company:"+company);
        System.out.println("Minimum CGPA Required:"+minimumCgpa);
        System.out.println("Skills Required:"+requiredSkills);
        System.out.println("-----------------------------");
    }
}
