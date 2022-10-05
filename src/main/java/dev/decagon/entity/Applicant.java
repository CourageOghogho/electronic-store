package dev.decagon.entity;

import dev.decagon.enums.Gender;
import dev.decagon.enums.Qualification;
import dev.decagon.enums.Role;

public class Applicant extends User{
    private String applicationID;
    private Role role;
    private Qualification qualification;

    public Applicant() {

    }


    public Applicant( String applicationID,String name, String email, Gender gender, Role role, Qualification qualification) {
        super(name, email, gender);
        this.applicationID = applicationID;
        this.role = role;
        this.qualification = qualification;
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

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }
}
