package com.barbershop.api.Models.Shop;

import com.barbershop.api.Models.BaseModel;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Services extends BaseModel {
    /*
    Url is the service photoUrl
    Duration is given in minutes
    Price is given in euros
     */
    public Long shopId;
    public Integer duration;
    public float price;
    public String name, url;

    public Services(long id, boolean active, Long shopId, Integer duration, float price, String name) {
        super(id, active);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
    }

    public Services(long id, boolean active, Long shopId, Integer duration, float price, String name, String url) {
        super(id, active);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
        this.url = url;
    }

    public Services() {
    }

    public Services(boolean active, Long shopId, Integer duration, float price, String name) {
        super(active);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
    }

    public Services(boolean active, Long shopId, Integer duration, float price, String name, String url) {
        super(active);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
        this.url = url;
    }

    public Services(long id, boolean active, Date createdUtc, Date modifiedUtc, Long shopId, Integer duration, float price, String name) {
        super(id, active, createdUtc, modifiedUtc);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
    }

    public Services(long id, boolean active, Date createdUtc, Date modifiedUtc, Long shopId, Integer duration, float price, String name, String url) {
        super(id, active, createdUtc, modifiedUtc);
        this.shopId = shopId;
        this.duration = duration;
        this.price = price;
        this.name = name;
        this.url = url;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
