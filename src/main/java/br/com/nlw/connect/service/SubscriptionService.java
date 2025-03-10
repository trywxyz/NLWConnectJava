package br.com.nlw.connect.service;

import br.com.nlw.connect.dto.SubscriptionRankingByUser;
import br.com.nlw.connect.dto.SubscriptionRankingItem;
import br.com.nlw.connect.dto.SubscriptionResponse;
import br.com.nlw.connect.exceptions.EventNotFoundException;
import br.com.nlw.connect.exceptions.SubscriptionConflictException;
import br.com.nlw.connect.exceptions.UserIndicadorNotFoundExeception;
import br.com.nlw.connect.model.Event;
import br.com.nlw.connect.model.Subscription;
import br.com.nlw.connect.model.User;
import br.com.nlw.connect.repository.EventRepository;
import br.com.nlw.connect.repository.SubscriptionRepository;
import br.com.nlw.connect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class SubscriptionService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId) {
        //RECUPERAR EVENTO PELO NOME
        Event evt = eventRepository.findByName(eventName); //recupera o evento
        if (evt == null) {
            throw new EventNotFoundException("Evento " + eventName + " não existe!");
        }

        User userRec = userRepository.findByEmail(user.getEmail());
        if (userRec == null) {
            userRec = userRepository.save(user);
        }

        User indicador = null;
        if (userId != null) {
            indicador = userRepository.findById(userId).orElse(null);
            if (indicador == null) {
                throw new UserIndicadorNotFoundExeception("Usúario " + eventName + " indicador não existe");
            }
        }
        //salvando
        Subscription subs = new Subscription();
        subs.setEvent(evt);
        subs.setSubscriber(userRec);
        subs.setIndication(indicador);

        Subscription tmpSub = subscriptionRepository.findByEventAndSubscriber(evt, userRec);
        if (tmpSub != null) {
            throw new SubscriptionConflictException("Já existe uma inscrição para o usúario" + userRec.getName() + " no evento " + evt.getTitle());
        }

        Subscription res = subscriptionRepository.save(subs);
        return new SubscriptionResponse(res.getSubscriptionNumber(), "http://codecraft.com/" + res.getEvent().getName() + "/" + res.getSubscriber().getId());

    }

    public List<SubscriptionRankingItem> getCompleteRanking(String name){
        Event evt = eventRepository.findByName(name);
        if(evt == null){
         throw new EventNotFoundException("Ranking do evento "+name+ " não existe!");
        }
        return subscriptionRepository.generateRanking(evt.getEventId());
    }

    public SubscriptionRankingByUser getRankingByUser(String name, Integer userId){
        List<SubscriptionRankingItem> ranking = getCompleteRanking(name);

        SubscriptionRankingItem item = ranking.stream().filter(i->i.userId().equals(userId)).findFirst().orElse(null);
        if(item == null){
            throw new UserIndicadorNotFoundExeception("Não há inscrições com indicação do usúario "+name);
        }

        Integer posicao = IntStream.range(0, ranking.size()).filter(pos -> ranking.get(pos).userId().equals(userId)).findFirst().getAsInt();
        return new SubscriptionRankingByUser(item, posicao+1);
    }
}
