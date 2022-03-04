package com.barbershop.api.Controller;

import com.barbershop.api.Models.Barber.Barber;
import com.barbershop.api.Repositories.IBarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/barbers/")
public class BarberController {

    @Autowired
    private IBarberRepository barberRepository;

    //region Get
    @GetMapping("")
    public /*List<Barber>*/ String getAllBarbers(){
        try {
            return this.barberRepository.findAll().toString();
        }catch(Exception e){
            return e.getMessage();
        }
    }
    //endregion

    //region Create
    @PostMapping("")
    public String createBarber(@RequestBody Barber barber){
        try {
            return ""+this.barberRepository.save(barber).getId();
        }catch(Exception e){
            return e.getMessage();
        }
    }
    //endregion
}
