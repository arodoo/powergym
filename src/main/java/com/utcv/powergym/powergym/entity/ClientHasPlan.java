package com.utcv.powergym.powergym.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "client_has_plan")
public class ClientHasPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long contractId;

    // Relación de muchos a uno con clients

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    // Relación de muchos a uno con plans

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonBackReference
    private Plan plan;


    // Relación de muchos a uno con users
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "contract_date")
    private Date contractDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_active")
    private boolean isActive;

    public ClientHasPlan() {
    }

    public ClientHasPlan(Client client, Plan plan, User user, Date contractDate, Date startDate, Date endDate) {
        this.client = client;
        this.plan = plan;
        this.user = user;
        this.contractDate = contractDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = true;
    }

    public boolean isPlanActive() {
        Date currentDate = new Date();
        return currentDate.compareTo(endDate) <= 0;
    }

    public void updatePlanStatus() {
        this.isActive = isPlanActive();
    }

    public void assignPlanToClient(Plan plan, User user, Client client) {
        this.plan = plan;
        this.user = user;
        this.client = client;
        this.contractDate = new Date();
        this.startDate = new Date();
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, plan.getNumDays());
        Date endDate = calendar.getTime();

        this.endDate = endDate;

        this.isActive = true;
    }

    @Override
    public String toString() {
        return "ClientHasPlan{" +
                ", client=" + client +
                ", plan=" + plan +
                ", user=" + user +
                ", contractDate=" + contractDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHasPlan that = (ClientHasPlan) o;
        return Objects.equals(client, that.client) && Objects.equals(plan, that.plan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, plan);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

