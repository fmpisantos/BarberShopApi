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
    public Integer shopId, duration;
    public float price;
    public String name;

    public Services(long id, boolean active, Integer shopId, Integer duration, float price, String name) {
        super(id, active);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
    }

    public Services() {
    }

    public Services(boolean active, Integer shopId, Integer duration, float price, String name) {
        super(active);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
    }

    public Services(long id, boolean active, Date createdUtc, Date modifiedUtc, Integer shopId, Integer duration, float price, String name) {
        super(id, active, createdUtc, modifiedUtc);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public Integer getDuration() {
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
