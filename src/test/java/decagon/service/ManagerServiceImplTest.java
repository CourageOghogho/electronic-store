package decagon.service;

import dev.decagon.entity.Applicant;
import dev.decagon.entity.Staff;
import dev.decagon.entity.Store;
import dev.decagon.enums.Gender;
import dev.decagon.enums.Qualification;
import dev.decagon.enums.Role;
import dev.decagon.exception.NoApplicantInListException;
import dev.decagon.service.ManagerServiceImpl;
import dev.decagon.service.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceImplTest {

    Store store;
    StoreServiceImpl storeService;
    Applicant applicant;
    Staff manager;
    ManagerServiceImpl managerService;

    @BeforeEach
    void setUp() {
        store=new Store();
        storeService=new StoreServiceImpl(store);

        applicant= new Applicant("AID100CSR","Mike",
                "mike@gmail.com", Gender.MALE, Role.CASHIER,
                Qualification.BSC);
        storeService.addApplicantToPool(applicant);

        manager=new Staff();
        manager.setRole(Role.MANAGER);
        storeService.addEmployee(manager);
        managerService=ManagerServiceImpl.managerService(manager,store);

    }

    @Test
    void hire() {
        int beforeSize=store.getEmployees().size();
        managerService.hire(applicant.getApplicationID());
        int afterSize=store.getEmployees().size();

        assertNotEquals(afterSize,beforeSize);

    }

    @Test
    void hireApplicantNotInPool() {
        store.getApplicantsPool().clear();

        assertThrows(NoApplicantInListException.class,()->managerService.hire(applicant.getApplicationID()));
    }
}