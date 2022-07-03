package com.barbershop.api.Controller;

import com.barbershop.api.Models.Relations.History;
import com.barbershop.api.Repositories.*;
import com.barbershop.api.Utils.CombineObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.barbershop.api.Utils.Calendar.fromDateToDateTime;

@RestController
@RequestMapping("history")
public class HistoryController {

    @Autowired
    private IHistoryRepository repository;

    @Autowired
    private IServicesRepository serviceRepo;

    @Autowired
    private IBarberShopRepository shopRepo;

    @Autowired
    private IBarberRepository barberRepo;

    @Autowired
    private IClientRepository clientRepo;

    //region List
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<History>> list(@RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "end", required = false) Integer end) {
        return new ResponseEntity<>(this.repository.findAll(PageRequest.of(start != null ? start : 0, end != null ? end : 10, Sort.by(sortBy != null ? sortBy : "id"))), HttpStatus.OK);
    }
    //endregion

    //region Create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody History barber) {
        barber.active = true;
        Optional entity = serviceRepo.findById(barber.serviceId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = shopRepo.findById(barber.shopId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = barberRepo.findById(barber.barberId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = clientRepo.findById(barber.clientId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(!repository.checkAvailability(barber.shopId, fromDateToDateTime(barber.dateTime) + "%", barber.barberId).isEmpty())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(this.repository.save(barber).getId(), HttpStatus.OK);
    }
    //endregion

    //region Get
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<History> get(@PathVariable("id") Long id) {
        Optional<History> entity = this.repository.findById(id);
        return new ResponseEntity<>(entity.isPresent() ? (entity.get()) : null, entity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    //endregion

    //region Update
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody History barber) {
        Optional entity = serviceRepo.findById(barber.serviceId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = shopRepo.findById(barber.shopId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = barberRepo.findById(barber.barberId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = clientRepo.findById(barber.clientId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = this.repository.findById(barber.id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            barber = new CombineObjects<History>().merge(barber, ((History) entity.get()));
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
        History barber = (History) entity.get();
        barber.modifiedUtc = new Date();
        barber.active = false;
        this.repository.save(barber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
