package model;

public class Student {
     private String name;
     private int id;
     private String email;
     private String password;
     private int branchId;
     private double cgpa;
     private String resumePath;

     public Student(String name,int id,String email,String password,int branchId,double cgpa,String resumePath){
        this.name=name;
        this.email=email;
        this.password=password;
        setCgpa(cgpa);
        this.id=id;
        this.branchId=branchId;
            this.resumePath=resumePath;
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
    public void setBranchId(int branchId) {
        this.branchId=branchId;
    }
    public void setResumePath(String resumePath) {
        this.resumePath=resumePath;
    }

    public void setEmail(String email) {
        this.email=email;
    }
    public void setPassword(String password) {
        this.password=password;
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
    public int  getBranchId() {
        return branchId;
    }
    public String  getResumePath() {
        return resumePath;
    }
    public String  getEmail() {
        return email;
    }
    public String  getPassword() {
        return password;
    }
    

    public void displayStudent() {
     System.out.println("Student Name:"+getName());
     System.out.println("Student Id:"+getId());
     System.out.println("Branch:"+getBranchId());
     System.out.println("CGPA:"+getCgpa());
     System.out.println("Resume Path:"+getResumePath());
     System.out.println("Email:"+getEmail());

}
}
