package com.utcv.powergym.powergym.runer;

import com.utcv.powergym.powergym.dto.ClientDTO;
import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.dto.PlanDTO;
import com.utcv.powergym.powergym.dto.UserDTO;
import com.utcv.powergym.powergym.entity.Client;
import com.utcv.powergym.powergym.entity.Plan;
import com.utcv.powergym.powergym.entity.User;
import com.utcv.powergym.powergym.mapper.ClientMapper;
import com.utcv.powergym.powergym.mapper.PlanMapper;
import com.utcv.powergym.powergym.mapper.UserMapper;
import com.utcv.powergym.powergym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PlanService planService;

    @Autowired
    private ClientHasPlanService clientHasPlanService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PlanMapper planMapper;

    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createAdmin();
        createClients();
        createPlans();
        assignPlanToClient();
    }

    private void assignPlanToClient() {

        UserDTO userDTO = new UserDTO();
        User loadedUser = userService.loadUserByEmail("haro.em@hotmail.com");
        userDTO = userMapper.fromUser(loadedUser);

        ClientDTO clientDTO = new ClientDTO();
        Client loadedClient = clientService.loadClientByClientLastName("Lastname1");
        clientDTO = clientMapper.fromClient(loadedClient);

        PlanDTO planDTO = new PlanDTO();
        Plan loadedPlan = planService.loadPlanByPlanName("Plan 1");
        planDTO = planMapper.fromPlan(loadedPlan);

        ClientHasPlanDTO clientHasPlanDTO = new ClientHasPlanDTO();
        clientHasPlanDTO.setContractDate(new Date());
        clientHasPlanDTO.setEndDate(new Date());
        clientHasPlanDTO.setStartDate(new Date());
        clientHasPlanDTO.setIsActive(true);
        clientHasPlanDTO.setClient(clientDTO);
        clientHasPlanDTO.setPlan(planDTO);
        clientHasPlanDTO.setUser(userDTO);

        clientHasPlanService.addPlanToClient(clientHasPlanDTO);
    }

    private void createPlans() {
        for (int i = 0; i < 5; i++) {

            PlanDTO planDTO = new PlanDTO();
            planDTO.setPlanName("Plan " + i);
            planDTO.setPrice((i * 10));
            planDTO.setDescription("Description of plan " + i);
            planDTO.setNumDays((i + 1));

            planService.createPlan(planDTO);
        }
    }


private void createClients() {
    for (int i = 0; i < 10; i++) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("Client" + i);
        clientDTO.setLastName("Lastname" + i);
        clientDTO.setEmail("client" + i + "@example.com");
        clientDTO.setPhoneNumber("1234567890");
        clientDTO.setBirthday(new Date());
        clientDTO.setEmergencyPhoneNumber("9876543210");
        clientDTO.setGender(Client.Gender.M);
        clientDTO.setActive(true);
        clientDTO.setStreetAddress("Street " + i);
        clientDTO.setAddressNumber(String.valueOf(i));
        clientDTO.setColony("Colony " + i);
        clientDTO.setCity("City " + i);
        clientDTO.setState("State " + i);
        clientDTO.setZipCode("12345" + i);

        clientService.createClient(clientDTO);
    }
}



    private void createAdmin() {
        userService.createUser("Arodo", "haro.em@hotmail.com", "123456");
        userService.assignRoleToUser("haro.em@hotmail.com", "Admin");

    }

    private void createRoles() {
        Arrays.asList("Admin", "User").forEach(role -> roleService.createRole(role));
    }
}
