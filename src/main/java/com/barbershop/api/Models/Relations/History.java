package com.barbershop.api.Models.Relations;

import com.barbershop.api.Models.BaseModel;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class History extends BaseModel {
    public Integer serviceId, shopId, barberId, clientId;
    public Date dateTime;

    public History(boolean active, Integer serviceId, Integer shopId, Integer barberId, Integer clientId, Date dateTime) {
        super(active);
        this.serviceId = serviceId;
        this.shopId = shopId;
        this.barberId = barberId;
        this.clientId = clientId;
        this.dateTime = dateTime;
    }

    public History(long id, boolean active, Integer serviceId, Integer shopId, Integer barberId, Integer clientId, Date dateTime) {
        super(id, active);
        this.serviceId = serviceId;
        this.shopId = shopId;
        this.barberId = barberId;
        this.clientId = clientId;
        this.dateTime = dateTime;
    }

    public History() {
    }

    public History(long id, boolean active, Date createdUtc, Date modifiedUtc, Integer serviceId, Integer shopId, Integer barberId, Integer clientId, Date dateTime) {
        super(id, active, createdUtc, modifiedUtc);
        this.serviceId = serviceId;
        this.shopId = shopId;
        this.barberId = barberId;
        this.clientId = clientId;
        this.dateTime = dateTime;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getBarberId() {
        return barberId;
    }

    public void setBarberId(Integer barberId) {
        this.barberId = barberId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
