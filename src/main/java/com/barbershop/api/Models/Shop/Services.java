package com.barbershop.api.Models.Shop;

import com.barbershop.api.Models.BaseModel;

public class Services extends BaseModel {
    /*
    Duration is given in minutes
    Price is given in euros
     */
    public int shopId, duration;
    public float price;
    public String name;
}
