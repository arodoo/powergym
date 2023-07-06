package com.utcv.powergym.powergym.utility;

import com.utcv.powergym.powergym.dao.ClientDao;
import com.utcv.powergym.powergym.dao.PlanDao;
import com.utcv.powergym.powergym.dao.RoleDao;
import com.utcv.powergym.powergym.dao.UserDao;
import com.utcv.powergym.powergym.entity.Client;
import com.utcv.powergym.powergym.entity.Plan;
import com.utcv.powergym.powergym.entity.Role;
import com.utcv.powergym.powergym.entity.User;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

public class OperationUtility {

    public static void userOperations(UserDao userDao) {
        createUser(userDao);
//        updateUser(userDao);
//        deleteUser(userDao);
//        fetchUsers(userDao);
    }

    public static void roleOperations(RoleDao roleDao) {
        createRole(roleDao);
//        updateRole(roleDao);
//fetchRoles(roleDao);
//deleteRole(roleDao);
    }

    public static void planOperations(PlanDao planDao) {
        createPlan(planDao);
//        fetchPlans(planDao);
//        updatePlan(planDao);
//        deletePlan(planDao);
    }

    public static void clientOperations(ClientDao clientDao, PlanDao planDao, UserDao userDao) {
   // createClient(clientDao);
//        fetchClients(clientDao);
//        updateClient(clientDao);
//        deleteClient(clientDao);
        assignPlanToClient(clientDao, planDao, userDao);
    }


    private static void deletePlan(PlanDao planDao) {
        Plan plan = planDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Plan not found"));
        planDao.delete(plan);
    }

    private static void updatePlan(PlanDao planDao) {
        Plan plan = planDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Plan not found"));
        plan.setPlanName("UpdatedPlanName");
        planDao.save(plan);
    }

    private static void fetchPlans(PlanDao planDao) {
        planDao.findAll().forEach(System.out::println);
    }

    private static void createPlan(PlanDao planDao) {
        Plan plan = new Plan("Plan 1", 1000, 30, "Plan 1");
        planDao.save(plan);
        Plan plan2 = new Plan("Plan 2", 2000, 60, "Plan 2");
        planDao.save(plan2);
    }


    private static void assignPlanToClient(ClientDao clientDao, PlanDao planDao, UserDao userDao) {
        Client client = clientDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        Plan plan = planDao.findByPlanName("Plan 1");
        User user = userDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("User not found"));
        client.assignPlanToClient(plan, user);
        System.out.println(client);
        clientDao.save(client);

    }


    public static void assignRolesToUsers(UserDao userDao, RoleDao roleDao) {
        Role role = roleDao.findByRoleName("ROLE_ADMIN");
        if (role == null) throw new EntityNotFoundException("Role Not Found");
        List<User> users = userDao.findAll();
        users.forEach(user -> {
            user.assignRoleToUser(role);
            userDao.save(user);
        });
    }
    private static void deleteClient(ClientDao clientDao) {
        Client client = clientDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        clientDao.delete(client);
    }

    private static void updateClient(ClientDao clientDao) {
        Client client = clientDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        client.setFirstName("UpdatedFirstName");
        clientDao.save(client);
    }

    private static void fetchClients(ClientDao clientDao) {
        clientDao.findAll().forEach(System.out::println);
    }

    private static void createClient(ClientDao clientDao) {
        Client client = new Client("John", "Doe", "johndoe@example.com", "1234567890", "9876543210", new Date(), Client.Gender.M, true, "Street Address", "123", "Colony Name", "City Name", "State Name", "12345");
        clientDao.save(client);
        Client client1 = new Client("John 2", "Doe", "johndoe2@example.com", "1234567890", "9876543210", new Date(), Client.Gender.M, true, "Street Address2", "1234", "Colony Name", "City Name", "State Name", "12345");
        clientDao.save(client1);
    }

    public static void assignRoleToUser(UserDao userDao, RoleDao roleDao) {
        Role role = roleDao.findByRoleName("ROLE_ADMIN");
        if (role == null) {
            throw new EntityNotFoundException("Role not found");
        }
        List<User> users = userDao.findAll();
        users.forEach(user -> {
            user.assignRoleToUser(role);
            userDao.save(user);
        });
    }

    private static void updateRole(RoleDao roleDao) {
        Role role = roleDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        role.setRoleName("UPDATED_ROLE_ADMIN");
        roleDao.save(role);
    }

    private static void deleteRole(RoleDao roleDao) {
        Role role = roleDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        roleDao.delete(role);
    }

    private static void fetchRoles(RoleDao roleDao) {
        roleDao.findAll().forEach(System.out::println);
    }

    private static void createRole(RoleDao roleDao) {
        Role role = new Role("ROLE_ADMIN");
        roleDao.save(role);
        Role role2 = new Role("ROLE_USER");
        roleDao.save(role2);
    }

    public static void createUser(UserDao userDao) {
        User user = new User("user1", "user1@gmail.com", "pass1");
        userDao.save(user);
        User user2 = new User("user2", "user2@gmail.com", "pass2");
        userDao.save(user2);
        User user3 = new User("user3", "user3@gmail.com", "pass3");
        userDao.save(user3);
    }

    public static void updateUser(UserDao userDao) {
        User user = userDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUserName("updated user");
        userDao.save(user);
    }

    public static void deleteUser(UserDao userDao) {
        User user = userDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userDao.delete(user);
    }

    public static void fetchUsers(UserDao userDao) {
        userDao.findAll().forEach(System.out::println);
    }


}
