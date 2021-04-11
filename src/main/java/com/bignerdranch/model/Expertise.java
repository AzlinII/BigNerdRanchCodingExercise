package com.bignerdranch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Expertise {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Expertise(){}

    public Expertise(String name){
        this.name = name;
    }

    public Expertise(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expertise expertise = (Expertise) o;
        return id.equals(expertise.id) && name.equals(expertise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Expertise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
