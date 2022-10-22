package dev.decagon.service;

import dev.decagon.entity.Applicant;
import dev.decagon.entity.Staff;
import dev.decagon.enums.Qualification;
import dev.decagon.enums.Role;

public interface ManagerService {
    public String hire(String applicationID);

    static Staff applicantTostaffMap(Applicant applicant){
        return new Staff(
                "EID00ANYCHR",
                applicant.getName(),
                applicant.getEmail(),
                applicant.getGender(),
                Role.CASHIER,
                Qualification.BSC
        );
    }
}
