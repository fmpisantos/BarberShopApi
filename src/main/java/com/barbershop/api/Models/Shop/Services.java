package com.barbershop.api.Models.Shop;

import com.barbershop.api.Models.BaseModel;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Services extends BaseModel {
    /*
    Duration is given in minutes
    Price is given in euros
     */
    public int shopId, duration;
    public float price;
    public String name;

    public Services(Date createdUtc, Date modifiedUtc, boolean active, int shopId, int duration, float price, String name) {
        super(createdUtc, modifiedUtc, active);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
