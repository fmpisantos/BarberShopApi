
package com.barbershop.api.Controller;

import com.barbershop.api.Models.Shop.Services;
import com.barbershop.api.Repositories.IBarberShopRepository;
import com.barbershop.api.Repositories.IServicesRepository;
import com.barbershop.api.Utils.CombineObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("serivices")
public class ServicesController {

    @Autowired
    private IServicesRepository repository;

    @Autowired
    private IBarberShopRepository shopRepo;

    //region List
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Services>> list(@RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "end", required = false) Integer end) {
        return new ResponseEntity<>(this.repository.findAll(PageRequest.of(start != null ? start : 0, end != null ? end : 10, Sort.by(sortBy != null ? sortBy : "id"))), HttpStatus.OK);
    }
    //endregion

    //region Create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody Services barber) {
        //barber.createdUtc = new Date();
        //barber.modifiedUtc = new Date();
        Optional entity = shopRepo.findById(barber.shopId);
        if(entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(this.repository.save(barber).getId(), HttpStatus.OK);
    }
    //endregion

    //region Get
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Services> get(@PathVariable("id") Long id) {
        Optional<Services> entity = this.repository.findById(id);
        return new ResponseEntity<>(entity.isPresent() ? ((Services) entity.get()) : null, entity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    //endregion

    //region Update
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Services barber) {
        Optional entity = shopRepo.findById(barber.shopId);
        if(entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        entity = this.repository.findById(barber.id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            barber = new CombineObjects<Services>().merge(barber, ((Services) entity.get()));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        barber.modifiedUtc = new Date();
        this.repository.save(barber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region Delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional entity = this.repository.findById(id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Services barber = (Services) entity.get();
        barber.modifiedUtc = new Date();
        barber.active = false;
        this.repository.save(barber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
