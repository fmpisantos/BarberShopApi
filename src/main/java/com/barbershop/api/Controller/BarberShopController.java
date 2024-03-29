
package com.barbershop.api.Controller;

import com.barbershop.api.Configuration;
import com.barbershop.api.Models.Client.Client;
import com.barbershop.api.Models.Shop.BarberShop;
import com.barbershop.api.Repositories.IBarberShopInstanceRepository;
import com.barbershop.api.Repositories.IBarberShopRepository;
import com.barbershop.api.Repositories.IClientRepository;
import com.barbershop.api.Repositories.IHistoryRepository;
import com.barbershop.api.Utils.Calendar;
import com.barbershop.api.Utils.CombineObjects;
import com.barbershop.api.Utils.Responses;
import org.json.JSONArray;
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
@RequestMapping("barbershop")
public class BarberShopController {

    @Autowired
    private IBarberShopRepository repository;

    @Autowired
    private IBarberShopInstanceRepository instanceRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IHistoryRepository historyRepository;

    //region List
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<BarberShop>> list(@RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "end", required = false) Integer end) {
        return new ResponseEntity<>(this.repository.findAll(PageRequest.of(start != null ? start : 0, end != null ? end : 10, Sort.by(sortBy != null ? sortBy : "id"))), HttpStatus.OK);
    }
    //endregion

    //region Create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody BarberShop barber) {
        barber.active = true;
        Optional instance = instanceRepository.findById(barber.getInstanceId());
        if (instance.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(this.repository.save(barber).getId(), HttpStatus.OK);
    }
    //endregion

    //region Get
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BarberShop> get(@PathVariable("id") Long id) {
        Optional<BarberShop> entity = this.repository.findById(id);
        return new ResponseEntity<>(entity.isPresent() ? ((BarberShop) entity.get()) : null, entity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    //endregion

    //region Update
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody BarberShop barber) {
        Optional instance = instanceRepository.findById(barber.getInstanceId());
        if (instance.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Optional entity = this.repository.findById(barber.id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            barber = new CombineObjects<BarberShop>().merge(barber, ((BarberShop) entity.get()));
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
        BarberShop barber = (BarberShop) entity.get();
        barber.modifiedUtc = new Date();
        barber.active = false;
        this.repository.save(barber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region Clients with history in this shop
    @RequestMapping(value = "/{id}/clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> listClients(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(clientRepository.findAllClientsByShopId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region Calendar of a single day
    @RequestMapping(value = "/{id}/history", method = RequestMethod.POST)
    public ResponseEntity<List<Map<String, Object>>> schedule(@PathVariable("id") Long id, @RequestBody String dateTime) {
        try {
            return new ResponseEntity<>(Responses.buildReturnListFromMap(historyRepository.historyByShopAndDate(id, dateTime + "%")), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region Get barber schedule of the day
    @RequestMapping(value = "/{id}/barber/{idbarber}", method = RequestMethod.GET, params = { "datetime", "duraction" }, produces = "application/json")
    public ResponseEntity<String> scheduleOfBarber(@PathVariable("id") Long id, @PathVariable("idbarber") Long idbarber, @RequestParam String datetime, @RequestParam(required = false) int duraction) {
        try {
            Map<String, Object> entity = Responses.buildReturnObjectFromMap(this.repository.findBarberShopRelation(id, idbarber));
            List<Map<String, Object>> dayReservations = Responses.buildReturnListFromMap(historyRepository.historyByShopBarberAndDate(id, idbarber, datetime + "%"));
            return new ResponseEntity<>(Calendar.generateCalendarFromDBString((String) entity.get("schedule"), dayReservations, Configuration.defaultDuration).toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/{id}/barber/{idbarber}", method = RequestMethod.GET, params = { "datetime" }, produces = "application/json")
    public ResponseEntity<String> scheduleOfBarber(@PathVariable("id") Long id, @PathVariable("idbarber") Long idbarber, @RequestParam String datetime) {
        try {
            Map<String, Object> entity = Responses.buildReturnObjectFromMap(this.repository.findBarberShopRelation(id, idbarber));
            List<Map<String, Object>> dayReservations = Responses.buildReturnListFromMap(historyRepository.historyByShopBarberAndDate(id, idbarber, datetime + "%"));
            return new ResponseEntity<>(Calendar.generateCalendarFromDBString((String) entity.get("schedule"), dayReservations, Configuration.defaultDuration).toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region Get Barber info for this shop
    @RequestMapping(value = "/{id}/barber/{idbarber}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getBarberRelation(@PathVariable("id") Long id, @PathVariable("idbarber") Long idbarber) {
        try {
            Map<String, Object> entity = Responses.buildReturnObjectFromMap(this.repository.findBarberShopRelation(id, idbarber));
            entity.put("schedule", Calendar.generateCalendarFromDBString((String) entity.get("schedule"), null, Configuration.defaultDuration).toString());
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region List Schedules
    @RequestMapping(value = "/{id}/schedule", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> listSchedules(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Responses.buildReturnListFromMap(this.repository.listSchedules(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region Get services
    @RequestMapping(value = "/{id}/services", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> listServices(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Responses.buildReturnListFromMap(this.repository.listServices(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region List Shop Barbers
    @RequestMapping(value = "/{id}/barbers", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> listBarbers(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Responses.buildReturnListFromMap(this.repository.listBarbers(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

}
