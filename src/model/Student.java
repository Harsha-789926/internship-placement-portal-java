package model;

public class Student {
     private String name;
     private int id;
     private String branch;
     private double cgpa;
     private String skills;
     public Student(String name,int id,String branch,double cgpa,String skills){
        this.name=name;
        setCgpa(cgpa);
        this.id=id;
        this.branch=branch;
        this.skills=skills;
    }

  
  
    public void setCgpa(double cgpa) {
        if(cgpa>=0 && cgpa<=10) {
            this.cgpa=cgpa;
        }else{
            System.out.println("Invalid CGPA");
        }
    }
    
    public void setName(String name) {
        this.name=name;
    }
    public void setBranch(String branch) {
        this.branch=branch;
    }
    public void setSkills(String skills) {
        this.skills=skills;
    }


    public double  getCgpa() {
        return cgpa;
    }
    public String  getName() {
        return name;
    }
    public int  getId() {
        return id;
    }
    public String  getBranch() {
        return branch;
    }
    public String  getSkills() {
        return skills;
    }

    public void displayStudent() {
     System.out.println("Student Name:"+getName());
     System.out.println("Student Id:"+getId());
     System.out.println("Branch:"+getBranch());
     System.out.println("CGPA:"+getCgpa());
     System.out.println("Skills:"+getSkills());

}
}
