package model;

public class Admin extends User {
    private String department;

    public Admin(int id,String name,String department){
        super(id,name);
        this.department=department;

    }
    public String getDepartment() {
        return department;
    }
    

    public void displayAdmin(){
        System.out.println("\n");
        System.out.println("-----Admin profile-----");
        super.displayuser();
        System.out.println("department: " + getDepartment());
        System.out.println("-----------------------");
























        
    }
    
}
