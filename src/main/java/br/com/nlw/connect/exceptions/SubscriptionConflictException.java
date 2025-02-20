package br.com.nlw.connect.exceptions;

public class SubscriptionConflictException extends RuntimeException{

    public SubscriptionConflictException(String msg){
        super(msg);
    }
}
