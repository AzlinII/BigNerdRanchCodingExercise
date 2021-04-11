package com.bignerdranch.exception;

public enum Type {
    EXPERTISE("expertise"),
    POST("post"),
    USER("user");

    public final String label;

    private Type(String label){
        this.label = label;
    }


}
