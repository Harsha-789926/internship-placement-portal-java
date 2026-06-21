package model;

public class Student extends User {
    private String branch;
    private double cgpa;
    private String skills;
    public Student(int id,String name,String branch,double cgpa,String skills){
        super(id,name);
        this.branch=branch;
        this.cgpa=cgpa;     
        this.skills=skills;
    }

    public void displaystudent(){
        System.out.println("\n");
        System.out.println("----Student Details-----");
        super.displayuser();
        System.out.println("Branch:"+branch);
        System.out.println("CGPA:"+cgpa);
        System.out.println("Skills:"+skills);
        System.out.println("-----------------------");
    }
}
