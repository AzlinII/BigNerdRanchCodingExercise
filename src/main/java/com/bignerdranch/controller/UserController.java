package com.bignerdranch.controller;

import com.bignerdranch.model.User;
import com.bignerdranch.object.UserUpdateRequest;
import com.bignerdranch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/users/{id}")
    public User readOne(@PathVariable Long id){
        return service.readOne(id);
    }

    @GetMapping("/users")
    public List<User> readAll(){
        return service.readAll();
    }

    @PostMapping("/users")
    public User create(@RequestBody UserUpdateRequest request){
        return service.create(request);
    }

    @PutMapping("/users/{id}")
    public User update(@RequestBody UserUpdateRequest request, @PathVariable Long id){
        return service.update(request, id);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
