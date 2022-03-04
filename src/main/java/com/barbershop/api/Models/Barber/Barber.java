package com.barbershop.api.Models.Barber;

import com.barbershop.api.Models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Barber extends BaseModel {
    @Column
    public int nif;
    @Column
    public String name, email, phone;

    public Barber(boolean active, int nif, String name, String email, String phone) {
        super(new Date(), new Date(), active);
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }
}
