package com.barbershop.api.Models.Relations;

import com.barbershop.api.Models.BaseModel;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BarberServices extends BaseModel {
    public Long serviceId, barberId;

    public BarberServices(long id, boolean active, Long serviceId, Long barberId) {
        super(id, active);
        this.serviceId = serviceId;
        this.barberId = barberId;
    }

    public BarberServices() {
    }

    public BarberServices(boolean active, Long serviceId, Long barberId) {
        super(active);
        this.serviceId = serviceId;
        this.barberId = barberId;
    }

    public BarberServices(long id, boolean active, Date createdUtc, Date modifiedUtc, Long serviceId, Long barberId) {
        super(id, active, createdUtc, modifiedUtc);
        this.serviceId = serviceId;
        this.barberId = barberId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getBarberId() {
        return barberId;
    }

    public void setBarberId(Long barberId) {
        this.barberId = barberId;
    }

}
