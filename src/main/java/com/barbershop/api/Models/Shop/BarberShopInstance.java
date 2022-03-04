package com.barbershop.api.Models.Shop;

import com.barbershop.api.Models.BaseModel;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BarberShopInstance extends BaseModel {
    public int nif;
    public String name, email, phone;

    public BarberShopInstance(Date createdUtc, Date modifiedUtc, boolean active, int nif, String name, String email, String phone) {
        super(createdUtc, modifiedUtc, active);
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getNif() {
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
