
package com.careerbridge.careerbridge_backend.dto;

public class AdminLoginResponse {

    private Integer adminId;
    private String name;
    private String email;
    private String department;
    private String message;

    public AdminLoginResponse() {
    }

    public AdminLoginResponse(Integer adminId, String name, String email,
                              String department, String message) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.message = message;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
