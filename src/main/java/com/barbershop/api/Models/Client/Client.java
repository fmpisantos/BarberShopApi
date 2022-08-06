package com.barbershop.api.Models.Client;

import com.barbershop.api.Models.Barber.Barber;
import com.barbershop.api.Models.BaseModel;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Client extends BaseModel {
    public Integer nif;
    public String name, email, phone;
    public Long barberId;

    public Client(boolean active, Integer nif, String name, String email, String phone, Long barberId) {
        super(active);
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.barberId = barberId;
    }

    public Client(Barber barber){
        super(barber.active);
        this.nif = barber.nif;
        this.name = barber.name;
        this.email = barber.email;
        this.phone = barber.phone;
        this.barberId = barber.id;
    }

    public Client(Object[] obj){
        super((Long) obj[0], (Boolean) obj[1], (Date) obj[2], (Date) obj[3]);
        this.email = (String) obj[4];
        this.name = (String) obj[5];
        this.nif = (Integer) obj[6];
        this.phone = (String) obj[7];
        this.barberId = (Long) obj[8];
    }

    public Client() { }

    public Client(long id, boolean active, Integer nif, String name, String email, String phone, Long barberId) {
        super(id, active);
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.barberId = barberId;
    }

    public Client(long id, boolean active, Date createdUtc, Date modifiedUtc, Integer nif, String name, String email, String phone, Long barberId) {
        super(id, active, createdUtc, modifiedUtc);
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.barberId = barberId;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
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

    public Long getBarberId(){
        return this.barberId;
    }

    public void setBarberId( Long barberId ){
        this.barberId = barberId;
    }
}
