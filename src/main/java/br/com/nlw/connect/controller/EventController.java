package br.com.nlw.connect.controller;

import br.com.nlw.connect.model.Event;
import br.com.nlw.connect.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping("/events")
    public Event addNewEvent(@RequestBody Event newEvent) {
        return service.addNewEvent(newEvent);
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }

    @GetMapping("/events/{name}")
    public ResponseEntity<Event> getEventByName(@PathVariable String name){
        Event evt = service.getByName(name);
        if(evt != null){ //VERIFICANDO SE EVENTO EXISTE NO BANCO DE DADOS
            return ResponseEntity.ok().body(evt);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
