package service;
import model.Admin;
import java.util.ArrayList;

public class AdminService {
    private ArrayList<Admin> admins;

    public AdminService(){
        admins=new ArrayList<>();
    }

    public void addAdmin(Admin admin){
        admins.add(admin);
    }

    public void viewAdmin(){
        for(Admin a:admins){
            a.displayAdmin();
        }
    }

    public Admin getAdminById(int id){
        for(Admin a:admins){
            if(a.getId()==id){
                return a;
            }
        }
        return null;
    }

    public void updateApplicationStatus(
        ApplicationService applicationService,
        int applicationId,
        String newStatus) {

    applicationService.updateStatus(applicationId, newStatus);
}
}
