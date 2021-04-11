package com.bignerdranch.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;
    private String email;

    @ManyToOne
    @JoinColumn(name="expertise_id")
    private Expertise expertise;

    public User(){}

    public User(String email, Expertise expertise){
        this.email = email;
        this.expertise = expertise;
    }

    public User(Long id, String email, Expertise expertise){
        this.id = id;
        this.email = email;
        this.expertise = expertise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Expertise getExpertise() {
        return expertise;
    }

    public void setExpertise(Expertise expertise) {
        this.expertise = expertise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && email.equals(user.email) && Objects.equals(expertise, user.expertise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, expertise);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", expertise=" + expertise +
                '}';
    }
}
