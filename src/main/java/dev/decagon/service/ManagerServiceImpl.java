package dev.decagon.service;

import dev.decagon.entity.Applicant;
import dev.decagon.entity.Staff;
import dev.decagon.entity.Store;
import dev.decagon.enums.Qualification;
import dev.decagon.enums.Role;
import dev.decagon.exception.ApplicantNotEligibleException;
import dev.decagon.exception.InvalidApplicationIdException;
import dev.decagon.exception.NoApplicantInListException;
import dev.decagon.exception.RoleAccessDeniedException;

import java.util.Objects;

public class ManagerServiceImpl implements ManagerService {

    private Store store;
    private Staff manager;


    private  ManagerServiceImpl(Staff manager,Store store){
        this.manager=manager;
        this.store=store;
    }

    public static ManagerServiceImpl managerService(Staff manager,Store store) {
        if(manager.getRole()==Role.MANAGER){
            return new ManagerServiceImpl(manager,store);
        }throw new RoleAccessDeniedException("Low authority level");
    }

    @Override
    public String hire(String applicationID) {
        if(store.getApplicantsPool().isEmpty())throw new NoApplicantInListException("No applicant applied for this role");
        for (Applicant applicant: store.getApplicantsPool()
             ) {
            if(Objects.equals(applicant.getApplicationID(), applicationID)){
                if(applicant.getRole()== Role.CASHIER && applicant.getQualification()== Qualification.BSC){
                    Staff cashier= new Staff("EID100CHR",applicant.getName(),applicant.getEmail(),
                            applicant.getGender(),applicant.getRole(),applicant.getQualification());
                    this.store.getEmployees().add(cashier);
                    this.store.getApplicantsPool().remove(applicant);
                    return "Successful, your EID is: "+cashier.getEmploymentID();
                }else {
                    throw new ApplicantNotEligibleException("Applicant with "+applicationID+" is not qualified");
                }
            }else throw new InvalidApplicationIdException("Applicant ID: "+applicationID);
        }
        return "Not eligible for this role";
    }
}
