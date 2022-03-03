package com.barbershop.api.Controller;

import com.barbershop.api.Repositories.BarberShopRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarberShopController {

    public BarberShopController() {

    }

    //region Test
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Test(){
        return "API is running";
    }
    //endregion
}
