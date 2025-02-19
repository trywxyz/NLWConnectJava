package br.com.nlw.connect.repository;

import br.com.nlw.connect.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
    public Event findByName(String name);
}
