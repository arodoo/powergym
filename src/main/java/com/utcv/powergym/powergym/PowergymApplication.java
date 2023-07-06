package com.utcv.powergym.powergym;

import com.utcv.powergym.powergym.dao.*;
import com.utcv.powergym.powergym.entity.ClientHasPlan;
import com.utcv.powergym.powergym.utility.OperationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PowergymApplication implements CommandLineRunner {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PlanDao planDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private ClientHasPlanDao clientHasPlanDao;

    public static void main(String[] args) {
        SpringApplication.run(PowergymApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        OperationUtility.userOperations(userDao);
//        OperationUtility.roleOperations(roleDao);
//        OperationUtility.planOperations(planDao);
//        OperationUtility.assignRolesToUsers(userDao, roleDao);
		OperationUtility.clientOperations(clientDao, planDao, userDao);

//        ClientHasPlan clientHasPlan = new ClientHasPlan();
//        clientHasPlan.setClient(clientDao.findById(1L).orElseThrow(() -> new Exception("Client not found")));
//        clientHasPlan.setPlan(planDao.findById(1L).orElseThrow(() -> new Exception("Plan not found")));
//        clientHasPlan.setUser(userDao.findById(1L).orElseThrow(() -> new Exception("User not found")));
//        clientHasPlan.setStartDate(new java.sql.Date(new java.util.Date().getTime()));
//        clientHasPlan.setEndDate(new java.sql.Date(new java.util.Date().getTime()));
//        clientHasPlanDao.save(clientHasPlan);

    }
}
