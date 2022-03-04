package com.barbershop.api.Controller;

import com.barbershop.api.Repositories.BarberShopRepository;
import com.barbershop.api.Repositories.IBarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarberShopController {

    @Autowired
    private IBarberRepository barberRepository;

    //region Test
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Test(){
        return "API is running";
    }
    //endregion
}
