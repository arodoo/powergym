package com.utcv.powergym.powergym.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "entry")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private Long entryId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    @Column(name = "entry_date")
    private Date entryDate;


    public Entry() {
    }

    public Entry(Client client, Date entryDate) {
        this.client = client;
        this.entryDate = entryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return Objects.equals(entryId, entry.entryId) && Objects.equals(client, entry.client) && Objects.equals(entryDate, entry.entryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, client, entryDate);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "entryId=" + entryId +
                ", client=" + client +
                ", entryDate=" + entryDate +
                '}';
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}
