package com.barbershop.api.Models.Relations;

import com.barbershop.api.Models.BaseModel;

public class ShopBarber extends BaseModel {
    public int shopId, barberId;
    public String photo, location, schedule;
}
