package com.kanivets.shares.exeptions;

public class NoEntityException extends Exception{
    public NoEntityException(String entityName ,Long id) {
        super("No "+ entityName +" with this id - " + id );
    }

    public NoEntityException(String message) {
        super(message);
    }
}

