package com.barbershop.api.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column(name = "CreatedUtc")
    public Date CreatedUtc;
    @Column(name = "ModifiedUtc")
    public Date ModifiedUtc;
    @Column(name = "Active")
    public boolean Active;

    public BaseModel(Date createdUtc, Date modifiedUtc, boolean Active) {
        CreatedUtc = createdUtc;
        ModifiedUtc = modifiedUtc;
        Active = Active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedUtc() {
        return CreatedUtc;
    }

    public void setCreatedUtc(Date createdUtc) {
        CreatedUtc = createdUtc;
    }

    public Date getModifiedUtc() {
        return ModifiedUtc;
    }

    public void setModifiedUtc(Date modifiedUtc) {
        ModifiedUtc = modifiedUtc;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        this.Active = active;
    }

}
