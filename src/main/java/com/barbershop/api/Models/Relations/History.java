package com.barbershop.api.Models.Relations;

import com.barbershop.api.Models.BaseModel;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class History extends BaseModel {
    public int serviceId, shopId, barberId, clientId;
    public Date dateTime;

    public History(Date createdUtc, Date modifiedUtc, boolean active, int serviceId, int shopId, int barberId, int clientId, Date dateTime) {
        super(createdUtc, modifiedUtc, active);
        this.serviceId = serviceId;
        this.shopId = shopId;
        this.barberId = barberId;
        this.clientId = clientId;
        this.dateTime = dateTime;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getBarberId() {
        return barberId;
    }

    public void setBarberId(int barberId) {
        this.barberId = barberId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
