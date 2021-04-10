package com.bignerdranch.controller;

import com.bignerdranch.assembler.UserAssembler;
import com.bignerdranch.exception.ControllerType;
import com.bignerdranch.exception.NotFoundException;
import com.bignerdranch.model.User;
import com.bignerdranch.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserAssembler assembler;

    @GetMapping("/users/{id}")
    public EntityModel<User> readOne(@PathVariable Long id){
        User user = repo.findById(id)
                .orElseThrow(() -> new NotFoundException(ControllerType.USER, id));
        return assembler.toModel(user);
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> readAll(){
        return null;
    }

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody User newValue){
        return null;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity update(@RequestBody User toUpdate, @PathVariable Long id){
        return null;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return null;
    }
    

}
