package dev.decagon.entity;

import dev.decagon.enums.Gender;
import dev.decagon.enums.Qualification;
import dev.decagon.enums.Role;

public class Staff extends User {
    private String employmentID;
    private Role role;
    private Qualification qualification;

    public Staff() {

    }

    public Staff( String employmentID,String name, String email, Gender gender, Role role, Qualification qualification) {
        super(name, email, gender);
        this.employmentID = employmentID;
        this.role = role;
        this.qualification = qualification;
    }

    public String getEmploymentID() {
        return employmentID;
    }

    public void setEmploymentID(String employmentID) {
        this.employmentID = employmentID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }
}
