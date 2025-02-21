package br.com.nlw.connect.controller;

import br.com.nlw.connect.dto.ErrorMessage;
import br.com.nlw.connect.dto.SubscriptionResponse;
import br.com.nlw.connect.exceptions.EventNotFoundException;
import br.com.nlw.connect.exceptions.SubscriptionConflictException;
import br.com.nlw.connect.exceptions.UserIndicadorNotFoundExeception;
import br.com.nlw.connect.model.User;
import br.com.nlw.connect.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionController {

    @Autowired
    SubscriptionService service;

    @PostMapping({"/subscription/{name}", "/subscription/{name}/{userId}"})
    public ResponseEntity<?> createSubscription(@PathVariable String name, @RequestBody User subscriber, @PathVariable(required = true) Integer userId){
        SubscriptionResponse res = service.createNewSubscription(name, subscriber, userId);

        try{
            if(res != null){
                return ResponseEntity.ok(res);
            }
        }catch (EventNotFoundException ex){
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
        catch (SubscriptionConflictException ex){
            return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
        }
        catch (UserIndicadorNotFoundExeception ex){
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/subscription/{name}/ranking")
    public ResponseEntity<?> generateRankingByEvent(@PathVariable String name){
        try{
            return ResponseEntity.ok(service.getCompleteRanking(name).subList(0,3));
        }catch (EventNotFoundException e){
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        }
    }

    @GetMapping("/subscription/{name}/ranking/{userId}")
    public ResponseEntity<?> generateRankingByEventAndUserId(@PathVariable String name, @PathVariable Integer userId){

        try{
            return ResponseEntity.ok(service.getRankingByUser(name, userId));
        }catch (Exception e){
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        }
    }
}
