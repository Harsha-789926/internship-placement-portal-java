package service;
import model.Company;
import java.util.ArrayList;

public class CompanyService {
    private ArrayList<Company> companies;
   
    public CompanyService(){
        companies=new ArrayList<>();
    }
    
    public void viewCompanies(){
        for(Company c :companies){
            c.displayCompany();
        }
    }

    public void addCompany(Company c){
        companies.add(c);
    }

    public Company getCompanyById(int id){
        for(Company c:companies){
            if(c.getCompanyId()==id){
                return c;
            }
        }
        return null;

    }
}
