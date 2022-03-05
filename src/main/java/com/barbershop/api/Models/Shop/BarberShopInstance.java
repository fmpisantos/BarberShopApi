package com.barbershop.api.Models.Shop;

import com.barbershop.api.Models.BaseModel;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BarberShopInstance extends BaseModel {
    public Integer nif;
    public String name, email, phone;

    public BarberShopInstance(boolean active, Integer nif, String name, String email, String phone) {
        super(active);
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public BarberShopInstance(long id, boolean active, Integer nif, String name, String email, String phone) {
        super(id, active);
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public BarberShopInstance() {
    }

    public BarberShopInstance(long id, boolean active, Date createdUtc, Date modifiedUtc, Integer nif, String name, String email, String phone) {
        super(id, active, createdUtc, modifiedUtc);
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Integer getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
