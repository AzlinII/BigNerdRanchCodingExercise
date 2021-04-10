package com.bignerdranch.service;

import com.bignerdranch.assembler.ExpertiseAssembler;
import com.bignerdranch.controller.ExpertiseController;
import com.bignerdranch.exception.ControllerType;
import com.bignerdranch.exception.NotFoundException;
import com.bignerdranch.model.Expertise;
import com.bignerdranch.respository.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ExpertiseService {

    @Autowired
    private ExpertiseRepository repo;

    @Autowired
    ExpertiseAssembler assembler;

    public EntityModel<Expertise> readOne(Long id){
        Expertise expertise = repo.findById(id)
                .orElseThrow(() -> new NotFoundException(ControllerType.EXPERTISE, id));
        return assembler.toModel(expertise);
    }

    public CollectionModel<EntityModel<Expertise>> readAll(){
        List<EntityModel<Expertise>> entities = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(entities, linkTo(methodOn(ExpertiseController.class).readAll()).withSelfRel());
    }

    public ResponseEntity create(@RequestBody Expertise newValue){
        return assembler.toResponseEntity(repo.save(newValue));
    }

    public ResponseEntity update(@RequestBody Expertise toUpdate, @PathVariable Long id){
        Expertise updated = repo.findById(id)
                .map(obj -> {
                    obj.setName(toUpdate.getName());
                    return repo.save(obj);
                })
                .orElseThrow(() -> new NotFoundException(ControllerType.EXPERTISE, id));
        return assembler.toResponseEntity(updated);
    }

    public ResponseEntity delete(@PathVariable Long id){
        repo.findById(id)
                .orElseThrow(() -> new NotFoundException(ControllerType.EXPERTISE, id));
        repo.deleteById(id);
        return ResponseEntity.accepted().build();
    }


}
