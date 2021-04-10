package com.bignerdranch.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(ControllerType type, Long id){
        super("Could not find " + type.label + " with id " + id);
    }

}
