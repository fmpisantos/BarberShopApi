package com.barbershop.api.Models.Shop;

import com.barbershop.api.Models.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class BarberShop extends BaseModel {
    private Long instanceId;
    public String privateName, publicName, location;

    public BarberShop(long id, boolean active, Long instanceId, String privateName, String publicName, String location) {
        super(id, active);
        this.instanceId = instanceId;
        this.privateName = privateName;
        this.publicName = publicName;
        this.location = location;
    }

    public BarberShop() {
    }

    public BarberShop(boolean active, Long instanceId, String privateName, String publicName, String location) {
        super(active);
        this.instanceId = instanceId;
        this.privateName = privateName;
        this.publicName = publicName;
        this.location = location;
    }

    public BarberShop(long id, boolean active, Date createdUtc, Date modifiedUtc, Long instanceId, String privateName, String publicName, String location) {
        super(id, active, createdUtc, modifiedUtc);
        this.instanceId = instanceId;
        this.privateName = privateName;
        this.publicName = publicName;
        this.location = location;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
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
