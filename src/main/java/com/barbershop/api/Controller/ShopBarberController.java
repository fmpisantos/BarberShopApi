
package com.barbershop.api.Controller;

import com.barbershop.api.Models.Relations.ShopBarber;
import com.barbershop.api.Repositories.IBarberRepository;
import com.barbershop.api.Repositories.IBarberShopRepository;
import com.barbershop.api.Repositories.IShopBarberRepository;
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
@RequestMapping("shopbarber")
public class ShopBarberController {

    @Autowired
    private IShopBarberRepository repository;

    @Autowired
    private IBarberShopRepository shopRepo;

    @Autowired
    private IBarberRepository barberRepo;

    //region List
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ShopBarber>> list(@RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "end", required = false) Integer end) {
        return new ResponseEntity<>(this.repository.findAll(PageRequest.of(start != null ? start : 0, end != null ? end : 10, Sort.by(sortBy != null ? sortBy : "id"))), HttpStatus.OK);
    }
    //endregion

    //region Create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody ShopBarber barber) {
        barber.active = true;
        Optional entity = shopRepo.findById(barber.shopId);
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
    public ResponseEntity<ShopBarber> get(@PathVariable("id") Long id) {
        Optional<ShopBarber> entity = this.repository.findById(id);
        return new ResponseEntity<>(entity.isPresent() ? ((ShopBarber) entity.get()) : null, entity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    //endregion

    //region Update
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody ShopBarber barber) {
        Optional entity = shopRepo.findById(barber.shopId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        entity = barberRepo.findById(barber.barberId);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        entity = this.repository.findById(barber.id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            barber = new CombineObjects<ShopBarber>().merge(barber, ((ShopBarber) entity.get()));
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
        ShopBarber barber = (ShopBarber) entity.get();
        barber.modifiedUtc = new Date();
        barber.active = false;
        this.repository.save(barber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion


}
