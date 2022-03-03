package com.barbershop.api.Models.Relations;

import com.barbershop.api.Models.BaseModel;

import java.util.Date;

public class History extends BaseModel {
    public int serviceId, shopId, barberId, clientId;
    public Date dateTime;
}
