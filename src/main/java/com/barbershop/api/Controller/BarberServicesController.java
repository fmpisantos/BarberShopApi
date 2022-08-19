
package com.barbershop.api.Controller;

import com.barbershop.api.Models.Relations.BarberServices;
import com.barbershop.api.Repositories.*;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("barberservices")
public class BarberServicesController {

    @Autowired
    private IBarberServiceRepository repository;

    @Autowired
    private IServicesRepository servicesRepository;

    @Autowired
    private IBarberRepository barberRepo;

    //region List
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<BarberServices>> list(@RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "end", required = false) Integer end) {
        return new ResponseEntity<>(this.repository.findAll(PageRequest.of(start != null ? start : 0, end != null ? end : 10, Sort.by(sortBy != null ? sortBy : "id"))), HttpStatus.OK);
    }
    //endregion

    //region Create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody BarberServices barber) {
        barber.active = true;
        Optional entity = servicesRepository.findById(barber.getServiceId());
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = barberRepo.findById(barber.barberId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(this.repository.save(barber).getId(), HttpStatus.OK);
    }
    //endregion

    //region Get
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BarberServices> get(@PathVariable("id") Long id) {
        Optional<BarberServices> entity = this.repository.findById(id);
        return new ResponseEntity<>(entity.isPresent() ? (entity.get()) : null, entity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    //endregion

    //region Update
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody BarberServices barber) {
        Optional entity = servicesRepository.findById(barber.getServiceId());
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = barberRepo.findById(barber.barberId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        entity = this.repository.findById(barber.id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            barber = new CombineObjects<BarberServices>().merge(barber, ((BarberServices) entity.get()));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        barber.modifiedUtc = new Date();
        try {
            this.repository.save(barber);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region Delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional entity = this.repository.findById(id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        BarberServices barber = (BarberServices) entity.get();
        barber.modifiedUtc = new Date();
        barber.active = false;
        this.repository.save(barber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion


}
