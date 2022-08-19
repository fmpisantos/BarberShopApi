package com.barbershop.api.Controller;

import com.barbershop.api.Models.Barber.Barber;
import com.barbershop.api.Models.Client.Client;
import com.barbershop.api.Repositories.IBarberRepository;
import com.barbershop.api.Repositories.IClientRepository;
import com.barbershop.api.Repositories.IHistoryRepository;
import com.barbershop.api.Utils.CombineObjects;
import com.barbershop.api.Utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/barbers")
public class BarberController {

    @Autowired
    private IBarberRepository barberRepository;

    @Autowired
    private IHistoryRepository historyRepository;

    @Autowired
    private IClientRepository clientRepository;

    //region List
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Barber>> list(@RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "end", required = false) Integer end) {
        return new ResponseEntity<>(this.barberRepository.findAll(PageRequest.of(start != null ? start : 0, end != null ? end : 10, Sort.by(sortBy != null ? sortBy : "id"))), HttpStatus.OK);
    }
    //endregion

    //region Create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody Barber barber) {
        barber.active = true;
        barber.id = this.barberRepository.save(barber).getId();
        this.clientRepository.save(new Client(barber));
        return new ResponseEntity<>(barber.id, HttpStatus.OK);
    }
    //endregion

    //region Get
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Barber> get(@PathVariable("id") Long id) {
        Optional<Barber> entity = this.barberRepository.findById(id);
        return new ResponseEntity<>(entity.isPresent() ? ((Barber) entity.get()) : null, entity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    //endregion

    //region Update
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Barber barber) {
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
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
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

    //region Get Schedule of the day
    @RequestMapping(value="/{id}/history", method = RequestMethod.POST)
    public ResponseEntity<List<Map<String, Object>>> schedule(@PathVariable("id") Long id, @RequestBody String dateTime){
        return new ResponseEntity<>(Responses.buildReturnListFromMap(historyRepository.historyByBarberAndDate(id, dateTime+"%")), HttpStatus.OK);
    }
    //endregion
}
