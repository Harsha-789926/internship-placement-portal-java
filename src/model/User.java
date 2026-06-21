package model;

public class User {
    protected int id;
    protected String name;

    public User(int id,String name){
        this.id=id;
        this.name=name;
    }

    public void  displayuser(){
        System.out.println("User ID: " + id);
        System.out.println("User Name: " + name);
        
    }

    public void displayonlyuser(){
        System.out.println("\n");
        System.out.println("-----User Details-----");
        System.out.println("User ID: " + id);
        System.out.println("User Name: " + name);
        System.out.println("-----------------------");
    }
}

