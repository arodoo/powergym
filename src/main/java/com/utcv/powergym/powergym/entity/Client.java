package com.utcv.powergym.powergym.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@Entity
@Table(name = "clients")
public class Client {

    public enum Gender {
        M, F, O
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;


    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Basic
    @Column(name = "phone_number", nullable = false, length = 45)
    private String phoneNumber;

    @Basic
    @Column(name = "emergency_phone_number", nullable = false, length = 45)
    private String emergencyPhoneNumber;

    @Basic
    @Column(name = "birthday", nullable = false, length = 45)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Basic
    @Column(name = "gender", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Basic
    @Column(name = "is_active", nullable = false, length = 1)
    private boolean isActive;

    @Basic
    @Column(name = "street_address", nullable = false, length = 20)
    private String streetAddress;

    @Basic
    @Column(name = "address_number", nullable = false, length = 5)
    private String addressNumber;

    @Basic
    @Column(name = "colony", nullable = false, length = 20)
    private String colony;

    @Basic
    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Basic
    @Column(name = "state", nullable = false, length = 20)
    private String state;

    @Basic
    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ClientHasPlan> clientPlans = new HashSet<>();

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Entry> entries = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String phoneNumber, String emergencyPhoneNumber, Date birthday, Gender gender, boolean isActive, String streetAddress, String addressNumber, String colony, String city, String state, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
        this.birthday = birthday;
        this.gender = gender;
        this.isActive = isActive;
        this.streetAddress = streetAddress;
        this.addressNumber = addressNumber;
        this.colony = colony;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emergencyPhoneNumber='" + emergencyPhoneNumber + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", isActive=" + isActive +
                ", streetAddress='" + streetAddress + '\'' +
                ", addressNumber='" + addressNumber + '\'' +
                ", colony='" + colony + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return isActive == client.isActive && Objects.equals(clientId, client.clientId) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(email, client.email) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(emergencyPhoneNumber, client.emergencyPhoneNumber) && Objects.equals(birthday, client.birthday) && gender == client.gender && Objects.equals(streetAddress, client.streetAddress) && Objects.equals(addressNumber, client.addressNumber) && Objects.equals(colony, client.colony) && Objects.equals(city, client.city) && Objects.equals(state, client.state) && Objects.equals(zipCode, client.zipCode) && Objects.equals(clientPlans, client.clientPlans) && Objects.equals(entries, client.entries);
    }

    @Transactional
    public void assignPlanToClient(Plan plan, User user) {
        ClientHasPlan clientHasPlan = new ClientHasPlan();

        clientHasPlan.setClient(this);
        clientHasPlan.setPlan(plan);
        clientHasPlan.setUser(user);
        clientHasPlan.setContractDate(new Date());
        clientHasPlan.setStartDate(new Date());

        Date currentDate = new Date();
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(Calendar.DAY_OF_MONTH, plan.getNumDays());

        Date endDate = calendar.getTime();

        clientHasPlan.setEndDate(endDate);

        // Añades la entidad a la colección de planes del cliente
        this.clientPlans.add(clientHasPlan);

        // Añades la entidad a la colección de clientes del plan
        plan.getClientPlans().add(clientHasPlan);
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Set<ClientHasPlan> getClientPlans() {
        return clientPlans;
    }

    public void setClientPlans(Set<ClientHasPlan> clientPlans) {
        this.clientPlans = clientPlans;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }
}
