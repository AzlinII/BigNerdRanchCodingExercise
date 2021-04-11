package com.bignerdranch.controller;

import com.bignerdranch.model.Expertise;
import com.bignerdranch.service.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpertiseController {

    @Autowired
    private ExpertiseService service;

    @GetMapping("/expertises/{id}")
    public Expertise readOne(@PathVariable Long id){
        return service.readOne(id);
    }

    @GetMapping("/expertises")
    public List<Expertise> readAll(){
        return service.readAll();
    }

    @PostMapping("/expertises")
    public Expertise create(@RequestBody Expertise newValue){
        return service.create(newValue);
    }

    @PutMapping("/expertises/{id}")
    public Expertise update(@RequestBody Expertise toUpdate, @PathVariable Long id){
        return service.update(toUpdate, id);
    }

    @DeleteMapping("/expertises/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
