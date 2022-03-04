package com.barbershop.api.Models.Relations;

import com.barbershop.api.Models.BaseModel;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class ShopBarber extends BaseModel {
    public int shopId, barberId;
    public String photo, location, schedule;

    public ShopBarber(Date createdUtc, Date modifiedUtc, boolean active, int shopId, int barberId, String photo, String location, String schedule) {
        super(createdUtc, modifiedUtc, active);
        this.shopId = shopId;
        this.barberId = barberId;
        this.photo = photo;
        this.location = location;
        this.schedule = schedule;
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
