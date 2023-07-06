package com.utcv.powergym.powergym.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "client_has_plan")
public class ClientHasPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private int contractId;

    // Relación de muchos a uno con clients
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Relación de muchos a uno con plans
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    // Relación de muchos a uno con users
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "contract_date")
    private Date contractDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public ClientHasPlan() {
    }

    public ClientHasPlan(Client client, Plan plan, User user, Date contractDate, Date startDate, Date endDate) {
        this.client = client;
        this.plan = plan;
        this.user = user;
        this.contractDate = contractDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ClientHasPlan{" +
                "contractId=" + contractId +
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
        if (!(o instanceof ClientHasPlan)) return false;
        ClientHasPlan that = (ClientHasPlan) o;
        return contractId == that.contractId && Objects.equals(client, that.client) && Objects.equals(plan, that.plan) && Objects.equals(user, that.user) && Objects.equals(contractDate, that.contractDate) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractId, client, plan, user, contractDate, startDate, endDate);
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
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
}

