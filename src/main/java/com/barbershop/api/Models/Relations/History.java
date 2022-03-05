package com.barbershop.api.Models.Relations;

import com.barbershop.api.Models.BaseModel;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class History extends BaseModel {
    public Long serviceId, shopId, barberId, clientId;
    public Date dateTime;

    public History(boolean active, Long serviceId, Long shopId, Long barberId, Long clientId, Date dateTime) {
        super(active);
        this.serviceId = serviceId;
        this.shopId = shopId;
        this.barberId = barberId;
        this.clientId = clientId;
        this.dateTime = dateTime;
    }

    public History(long id, boolean active, Long serviceId, Long shopId, Long barberId, Long clientId, Date dateTime) {
        super(id, active);
        this.serviceId = serviceId;
        this.shopId = shopId;
        this.barberId = barberId;
        this.clientId = clientId;
        this.dateTime = dateTime;
    }

    public History() {
    }

    public History(long id, boolean active, Date createdUtc, Date modifiedUtc, Long serviceId, Long shopId, Long barberId, Long clientId, Date dateTime) {
        super(id, active, createdUtc, modifiedUtc);
        this.serviceId = serviceId;
        this.shopId = shopId;
        this.barberId = barberId;
        this.clientId = clientId;
        this.dateTime = dateTime;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getBarberId() {
        return barberId;
    }

    public void setBarberId(Long barberId) {
        this.barberId = barberId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
