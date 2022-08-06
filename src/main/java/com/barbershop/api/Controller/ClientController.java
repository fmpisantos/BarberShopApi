
package com.barbershop.api.Controller;

import com.barbershop.api.Models.Client.Client;
import com.barbershop.api.Models.LoginIds.LoginIds;
import com.barbershop.api.Repositories.IBarberRepository;
import com.barbershop.api.Repositories.IClientRepository;
import com.barbershop.api.Repositories.IHistoryRepository;
import com.barbershop.api.Repositories.ILoginIdsRepository;
import com.barbershop.api.Utils.CombineObjects;
import com.barbershop.api.Utils.Responses;
import org.json.JSONObject;
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
@RequestMapping("client")
public class ClientController {

    @Autowired
    private IClientRepository repository;
    @Autowired
    private IHistoryRepository historyRepository;
    @Autowired
    private IBarberRepository barberRepository;
    @Autowired
    private ILoginIdsRepository loginIdsRepository;

    //region List
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Client>> list(@RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "end", required = false) Integer end) {
        return new ResponseEntity<>(this.repository.findAll(PageRequest.of(start != null ? start : 0, end != null ? end : 10, Sort.by(sortBy != null ? sortBy : "id"))), HttpStatus.OK);
    }
    //endregion

    //region Create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Client client, @RequestParam(value = "type", required = true) String type) {
        Long id;
        client.active = true;
        if(client.barberId != null){
            Optional barber = this.barberRepository.findById(client.barberId);
            if(barber.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<LoginIds> clientId = this.loginIdsRepository.findByTypeAndId(type,client.phone);
        if(!clientId.isEmpty()){
            id = clientId.get(0).getId();
            Client c = this.repository.findById(id).get();
            return new ResponseEntity<>(new JSONObject().put("id",id).put("name", c.getName()).toString(), HttpStatus.OK);
        }
        List<Client> entity = this.repository.findByNameAndPhone(client.name, client.phone);
        if (!entity.isEmpty()) {
            Client c = entity.get(0);
            return new ResponseEntity<>(new JSONObject().put("id", c.getId()).put("name", c.getName()).toString(), HttpStatus.OK);
        }
        id = this.repository.save(client).getId();
        if(type.equalsIgnoreCase("google") || type.equalsIgnoreCase("apple"))
            this.loginIdsRepository.save(new LoginIds(type,id, client.getPhone()));
        return new ResponseEntity<>(new JSONObject().put("id",id).put("name",client.getName()).toString(), HttpStatus.OK);
    }
    //endregion

    //region Get
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> get(@PathVariable("id") Long id) {
        Optional<Client> entity = this.repository.findById(id);
        return new ResponseEntity<>(entity.isPresent() ? (entity.get()) : null, entity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    //endregion

    //region Update
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Client barber) {
        Optional entity = this.repository.findById(barber.id);
        if (entity.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            barber = new CombineObjects<Client>().merge(barber, ((Client) entity.get()));
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
        Client barber = (Client) entity.get();
        barber.modifiedUtc = new Date();
        barber.active = false;
        this.repository.save(barber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region Get client history
    @RequestMapping(value = "/{id}/history", method = RequestMethod.POST)
    public ResponseEntity<List<Map<String, Object>>> schedule(@PathVariable("id") Long id, @RequestBody String dateTime) {
        try {
            return new ResponseEntity<>(Responses.buildReturnListFromMap(historyRepository.historyByClientAndDate(id, dateTime + "%")), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion
}
