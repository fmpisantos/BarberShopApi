package com.barbershop.api.Controller;

import com.barbershop.api.Models.Barber.Barber;
import com.barbershop.api.Repositories.IBarberRepository;
import com.barbershop.api.Utils.CombineObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BarberController {

    @Autowired
    private IBarberRepository barberRepository;

    //region List
    @RequestMapping(value = "/barbers", method = RequestMethod.GET)
    public ResponseEntity<Page<Barber>> getAllBarbers(@RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "end", required = false) Integer end) {
        return new ResponseEntity<>(this.barberRepository.findAll(PageRequest.of(start != null ? start : 0, end != null ? end : 10, Sort.by(sortBy != null ? sortBy : "id"))), HttpStatus.OK);
    }
    //endregion

    //region Create
    @RequestMapping(value = "/barbers", method = RequestMethod.POST)
    public ResponseEntity<Long> createBarber(@RequestBody Barber barber) {
        //barber.createdUtc = new Date();
        //barber.modifiedUtc = new Date();
        return new ResponseEntity<>(this.barberRepository.save(barber).getId(), HttpStatus.OK);
    }
    //endregion

    //region Get
    @RequestMapping(value = "/barbers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Barber> getBarber(@PathVariable("id") Long id) {
        Optional<Barber> entity = this.barberRepository.findById(id);
        return new ResponseEntity<>(entity.isPresent() ? ((Barber) entity.get()) : null, entity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    //endregion

    //region Update
    @RequestMapping(value = "/barbers", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBarber(@RequestBody Barber barber) {
        Optional entity = this.barberRepository.findById(barber.id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            barber = new CombineObjects<Barber>().merge(barber, ((Barber) entity.get()));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        barber.modifiedUtc = new Date();
        this.barberRepository.save(barber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region Delete
    @RequestMapping(value = "/barbers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBarber(@PathVariable("id") Long id) {
        Optional entity = this.barberRepository.findById(id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Barber barber = (Barber) entity.get();
        barber.modifiedUtc = new Date();
        barber.active = false;
        this.barberRepository.save(barber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
