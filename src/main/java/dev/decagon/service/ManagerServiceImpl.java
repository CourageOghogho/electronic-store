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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ManagerServiceImpl implements ManagerService {

    private Store store;
    private Staff manager;


    private ManagerServiceImpl(Staff manager, Store store) {
        this.manager = manager;
        this.store = store;
    }

    public static ManagerServiceImpl managerService(Staff manager, Store store) {
        if (manager.getRole() == Role.MANAGER) {
            return new ManagerServiceImpl(manager, store);
        }
        throw new RoleAccessDeniedException("Low authority level");
    }


    public String hireDeprecated(String applicationID) {
        if (store.getApplicantsPool().isEmpty())
            throw new NoApplicantInListException("No applicant applied for this role");
        for (Applicant applicant : store.getApplicantsPool()
        ) {
            if (Objects.equals(applicant.getApplicationID(), applicationID)) {
                if (applicant.getRole() == Role.CASHIER && applicant.getQualification() == Qualification.BSC) {
                    Staff cashier = new Staff("EID100CHR", applicant.getName(), applicant.getEmail(),
                            applicant.getGender(), applicant.getRole(), applicant.getQualification());
                    this.store.getEmployees().add(cashier);
                    this.store.getApplicantsPool().remove(applicant);
                    return "Successful, your EID is: " + cashier.getEmploymentID();
                } else {
                    throw new ApplicantNotEligibleException("Applicant with " + applicationID + " is not qualified");
                }
            } else throw new InvalidApplicationIdException("Applicant ID: " + applicationID);
        }
        return "Not eligible for this role";
    }


    @Override
    public String hire(String applicantID) {
        Optional<Staff> cashierOptional = store.getApplicantsPool().stream()
                .filter(applicant -> applicant.getApplicationID().equals(applicantID))
                .filter(applicant -> applicant.getQualification().equals(Qualification.BSC))
                .map(ManagerService::applicantTostaffMap)
                .findAny();
        AtomicReference<String> result = new AtomicReference<>("");
        cashierOptional.ifPresentOrElse(cashier -> {
            store.getEmployees().add(cashier);
            result.set("Successful, your EID is: " + cashier.getEmploymentID());
        }, () -> {
            throw new ApplicantNotEligibleException("Applicant with "+applicantID+" is not qualified");
        });
        return result.get();
    }
}
