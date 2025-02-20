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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
