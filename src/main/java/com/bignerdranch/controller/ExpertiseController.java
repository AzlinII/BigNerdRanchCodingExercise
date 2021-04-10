package com.bignerdranch.controller;

import com.bignerdranch.assembler.ExpertiseAssembler;
import com.bignerdranch.exception.ControllerType;
import com.bignerdranch.exception.NotFoundException;
import com.bignerdranch.model.Expertise;
import com.bignerdranch.respository.ExpertiseRepository;
import com.bignerdranch.service.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ExpertiseController {

    @Autowired
    private ExpertiseRepository repo;

    @Autowired
    private ExpertiseService service;

    @Autowired
    private ExpertiseAssembler assembler;

    @GetMapping("/expertises/{id}")
    public EntityModel<Expertise> readOne(@PathVariable Long id){
        return service.readOne(id);
    }

    @GetMapping("/expertises")
    public CollectionModel<EntityModel<Expertise>> readAll(){
        return service.readAll();
    }

    @PostMapping("/expertises")
    public ResponseEntity create(@RequestBody Expertise newValue){
        return service.create(newValue);
    }

    @PutMapping("/expertises/{id}")
    public ResponseEntity update(@RequestBody Expertise toUpdate, @PathVariable Long id){
        return service.update(toUpdate, id);
    }

    @DeleteMapping("/expertises/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return service.delete(id);
    }

}
