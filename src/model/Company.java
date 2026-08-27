package model;

public class Company {
    private int companyId;
    private String companyName;
    private String email;
    private String location;

    public Company(int companyId,String companyName,String email,String location){
        this.companyId=companyId;
        this.companyName=companyName;
        this.email=email;
        this.location=location;
    }

    public int getCompanyId(){
        return companyId;
    }

    public String getCompanyName(){
        return companyName;
    }

    public String getEmail(){
        return email;
    }

    public String getLocation(){
        return location;
    }

    public void displayCompany(){
        System.out.println("Company Id:"+companyId );
        System.out.println("Company Name:"+companyName );
        System.out.println("Email:"+email );
        System.out.println("Location:"+location );
    }
}
