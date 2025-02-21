package br.com.nlw.connect.repository;

import br.com.nlw.connect.dto.SubscriptionRankingItem;
import br.com.nlw.connect.model.Event;
import br.com.nlw.connect.model.Subscription;
import br.com.nlw.connect.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    public Subscription findByEventAndSubscriber(Event evt, User user);

    @Query( value  = "select count(subscription_number) as quantidade, indication_user_id, user_name " +
            "from subscription inner join users " +
            "on subscription.indication_user_id = users.user_id " +
            "where indication_user_id is not null " +
            "and event_id = :eventId " +
            "group by indication_user_id " +
            "order by quantidade desc;", nativeQuery = true)
    public List<SubscriptionRankingItem> generateRanking(@Param("eventId") Integer eventId);
}
