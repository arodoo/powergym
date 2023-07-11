package com.utcv.powergym.powergym.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;

    @Basic
    @Column(name = "plan_name", nullable = false, length = 45)
    private String planName;

    @Basic
    @Column(name = "price", nullable = false)
    private int price;

    @Basic
    @Column(name = "num_days", nullable = false)
    private int numDays;

    @Basic
    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @OneToMany(mappedBy = "plan", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ClientHasPlan> clientPlans = new HashSet<>();

    public Plan() {
    }

    public Plan(String planName, int price, int numDays, String description) {
        this.planName = planName;
        this.price = price;
        this.numDays = numDays;
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plan)) return false;
        Plan plan = (Plan) o;
        return planId == plan.planId && price == plan.price && numDays == plan.numDays && Objects.equals(planName, plan.planName) && Objects.equals(description, plan.description) && Objects.equals(clientPlans, plan.clientPlans);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", planName='" + planName + '\'' +
                ", price=" + price +
                ", numDays=" + numDays +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ClientHasPlan> getClientPlans() {
        return clientPlans;
    }

    public void setClientPlans(Set<ClientHasPlan> clientPlans) {
        this.clientPlans = clientPlans;
    }
}
