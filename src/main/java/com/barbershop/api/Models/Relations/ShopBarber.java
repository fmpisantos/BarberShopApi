package com.barbershop.api.Models.Relations;

import com.barbershop.api.Models.BaseModel;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class ShopBarber extends BaseModel {
    public Long shopId, barberId;
    public String photo, location, schedule;

    public ShopBarber(long id, boolean active, Long shopId, Long barberId, String photo, String location, String schedule) {
        super(id, active);
        this.shopId = shopId;
        this.barberId = barberId;
        this.photo = photo;
        this.location = location;
        this.schedule = schedule;
    }

    public ShopBarber() {
    }

    public ShopBarber(boolean active, Long shopId, Long barberId, String photo, String location, String schedule) {
        super(active);
        this.shopId = shopId;
        this.barberId = barberId;
        this.photo = photo;
        this.location = location;
        this.schedule = schedule;
    }

    public ShopBarber(long id, boolean active, Date createdUtc, Date modifiedUtc, Long shopId, Long barberId, String photo, String location, String schedule) {
        super(id, active, createdUtc, modifiedUtc);
        this.shopId = shopId;
        this.barberId = barberId;
        this.photo = photo;
        this.location = location;
        this.schedule = schedule;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
