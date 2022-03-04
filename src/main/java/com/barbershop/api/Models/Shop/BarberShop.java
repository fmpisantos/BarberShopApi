package com.barbershop.api.Models.Shop;

import com.barbershop.api.Models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class BarberShop extends BaseModel {
    public int instanceId;
    public String privateName, publicName, location;

    public BarberShop(Date createdUtc, Date modifiedUtc, boolean active, int instanceId, String privateName, String publicName, String location) {
        super(createdUtc, modifiedUtc, active);
        this.instanceId = instanceId;
        this.privateName = privateName;
        this.publicName = publicName;
        this.location = location;
    }

    public int getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    public String getPrivateName() {
        return privateName;
    }

    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
