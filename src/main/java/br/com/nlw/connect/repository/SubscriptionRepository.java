package br.com.nlw.connect.repository;

import br.com.nlw.connect.model.Event;
import br.com.nlw.connect.model.Subscription;
import br.com.nlw.connect.model.User;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    public Subscription findByEventAndSubscriber(Event evt, User user);
}
