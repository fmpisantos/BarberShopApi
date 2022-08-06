package com.barbershop.api.Models.LoginIds;

import com.barbershop.api.Models.BaseModel;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class LoginIds extends BaseModel {
    public String type, platformId;
    public Long clientId;

    public LoginIds(String type, Long clientId, String platformId) {
        super(true);
        this.type = type;
        this.platformId = platformId;
        this.clientId = clientId;
    }

    public LoginIds(Object[] obj){
        super((Long) obj[0], true, (Date) obj[2], (Date) obj[3]);
        this.type = (String) obj[0];
        this.platformId = (String) obj[2];
        this.clientId = (Long) obj[1];
    }

    public LoginIds() { }

    public String getType() {
        return this.type;
    }

    public String getPlatformId() {
        return this.platformId;
    }

    public Long getClientId() {
        return this.clientId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public void setClientId(Long id) {
        this.clientId = id;
    }
}
