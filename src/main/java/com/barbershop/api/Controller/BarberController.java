package com.barbershop.api.Controller;

import com.barbershop.api.Models.Barber.Barber;
import com.barbershop.api.Repositories.IBarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarberController {

    @Autowired
    private IBarberRepository barberRepository;

    //region Get
    @RequestMapping(value ="/barbers", method = RequestMethod.GET)
    public /*List<Barber>*/ String getAllBarbers(){
        try {
            return this.barberRepository.findAll().toString();
        }catch(Exception e){
            return e.getMessage();
        }
    }
    //endregion

    //region Create
    @RequestMapping(value ="/barbers", method = RequestMethod.POST)
    public String createBarber(@RequestBody Barber barber){
        try {
            return ""+this.barberRepository.save(barber).getId();
        }catch(Exception e){
            return e.getMessage();
        }
    }
    //endregion
}
