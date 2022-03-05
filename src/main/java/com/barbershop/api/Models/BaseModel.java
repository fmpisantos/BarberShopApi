package com.barbershop.api.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.Date;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    public long id;
    @JsonProperty("active")
    public boolean active;
    @JsonProperty("createdUtc")
    public Date createdUtc;
    @JsonProperty("modifiedUtc")
    public Date modifiedUtc;

    public BaseModel(){
        this.createdUtc = new Date();
        this.modifiedUtc = new Date();
    }

    public BaseModel(long id, boolean active, Date createdUtc, Date modifiedUtc) {
        this.id = id;
        this.createdUtc = createdUtc;
        this.modifiedUtc = modifiedUtc;
        this.active = active;
    }

    public BaseModel(boolean active) {
        this.active = active;
        this.createdUtc = new Date();
        this.modifiedUtc = new Date();
    }

    public BaseModel(long id,boolean active) {
        this.id = id;
        this.active = active;
        this.modifiedUtc = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(Date createdUtc) {
        this.createdUtc = createdUtc;
    }

    public Date getModifiedUtc() {
        return modifiedUtc;
    }

    public void setModifiedUtc(Date modifiedUtc) {
        this.modifiedUtc = modifiedUtc;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
