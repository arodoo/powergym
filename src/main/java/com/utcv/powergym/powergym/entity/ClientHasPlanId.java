package com.utcv.powergym.powergym.entity;

import java.io.Serializable;
import java.util.Objects;

public class ClientHasPlanId implements Serializable {

    private Long client;
    private Long plan;

    public ClientHasPlanId() {
    }

    public ClientHasPlanId(Long client, Long plan) {
        this.client = client;
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHasPlanId that = (ClientHasPlanId) o;
        return Objects.equals(client, that.client) && Objects.equals(plan, that.plan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, plan);
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getPlan() {
        return plan;
    }

    public void setPlan(Long plan) {
        this.plan = plan;
    }
}
