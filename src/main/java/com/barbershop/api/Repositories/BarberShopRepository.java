package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Shop.BarberShop;
import com.barbershop.api.Models.Shop.BarberShopInstance;

import java.util.ArrayList;
import java.util.List;

public class BarberShopRepository {
    //region Find
    public BarberShopInstance FindInstance(int id){
        return null;
    }

    public BarberShop Find(int id){
        return null;
    }
    //endregion
    //region Update
    public boolean UpdateInstance(int id){
        return true;
    }

    public boolean Update(int id){
        return true;
    }
    //endregion
    //region Delete
    public boolean DeleteInstance(int id){
        // TODO: Active = false to all barbershops with instanceId = id and Instance with id = id
        return true;
    }

    public boolean Delete(int id){
        // TODO: Active = false to Shop with id = id
        return true;
    }
    //endregion
    //region Search
    //TODO:Should receive a filter with pagination and order by information
    public List<BarberShop> List(){
        return new ArrayList<BarberShop>();
    }
    //endregion
}
