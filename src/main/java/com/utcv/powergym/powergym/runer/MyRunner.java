package com.utcv.powergym.powergym.runer;

import com.utcv.powergym.powergym.dto.*;
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
    private EntryService entryService;

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
//        createRoles();
//        createAdmin();
//        createClients();
//        createPlans();
//        assignPlanToClient();
//        addEntries();
    }

    private void addEntries() {
        for (int j = 0; j < 5; j++) {
            for (long i = 1; i < 19; i++) {
                Client loadedClient = clientService.loadClientById(i);
                EntryDTO entryDTO = new EntryDTO();
                entryDTO.setClient(clientMapper.fromClient(loadedClient));
                entryDTO.setEntryDate(new Date());
                entryService.addEntryToClient(entryDTO);
            }
        }
    }

    private void assignPlanToClient() {
        for (int i = 0; i < 20; i++) {

            UserDTO userDTO = new UserDTO();
            User loadedUser = userService.loadUserByEmail("haro.em@hotmail.com");
            userDTO = userMapper.fromUser(loadedUser);

            ClientDTO clientDTO = new ClientDTO();
            Client loadedClient = clientService.loadClientByClientLastName("Lastname" + i);
            clientDTO = clientMapper.fromClient(loadedClient);

            PlanDTO planDTO = new PlanDTO();
            Plan loadedPlan = planService.loadPlanByPlanName("Plan 1");
            planDTO = planMapper.fromPlan(loadedPlan);

            ClientHasPlanDTO clientHasPlanDTO = new ClientHasPlanDTO();
            clientHasPlanDTO.setContractDate(new Date());
            clientHasPlanDTO.setStartDate(new Date());
            clientHasPlanDTO.setIsActive(true);
            clientHasPlanDTO.setClient(clientDTO);
            clientHasPlanDTO.setPlan(planDTO);
            clientHasPlanDTO.setUser(userDTO);

            for (int j = 0; j < 20; j++) {
                clientHasPlanService.addPlanToClient(clientHasPlanDTO);
            }
        }
    }

    private void createPlans() {
        for (int i = 0; i < 20; i++) {

            PlanDTO planDTO = new PlanDTO();
            planDTO.setPlanName("Plan " + i);
            planDTO.setPrice((i * 10));
            planDTO.setDescription("Description of plan " + i);
            planDTO.setNumDays((30));

            planService.createPlan(planDTO);
        }
    }


    private void createClients() {
        for (int i = 0; i < 20; i++) {
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
        for (int i = 0; i < 20; i++) {
            userService.createUser("Arodo", "haro.em@hotmail" + i + ".com", "123456");
            userService.assignRoleToUser("haro.em@hotmail" + i + ".com", "User");
        }
        userService.createUser("Arodo", "haro.em@hotmail.com", "123456");
        userService.assignRoleToUser("haro.em@hotmail.com", "Admin");
    }

    private void createRoles() {
        Arrays.asList("Admin", "User").forEach(role -> roleService.createRole(role));
    }
}
