package com.bignerdranch.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Type type, Long id){
        super("Could not find " + type.label + " with id " + id);
    }

}
