package br.com.nlw.connect.service;

import br.com.nlw.connect.model.Event;
import br.com.nlw.connect.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public Event addNewEvent(Event event) {
        //Gerando o name
        event.setName(event.getTitle().toLowerCase().replaceAll(" ", null));
        return repository.save(event);
    }

    public List<Event> getAllEvents() {
        return (List<Event>) repository.findAll();

    }

    public Event getByName(String name) {
        return repository.findByName(name);
    }

}
