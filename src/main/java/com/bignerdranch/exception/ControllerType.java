package com.bignerdranch.exception;

public enum ControllerType {
    EXPERTISE("expertise"),
    POST("post"),
    USER("user");

    public final String label;

    private ControllerType(String label){
        this.label = label;
    }


}
