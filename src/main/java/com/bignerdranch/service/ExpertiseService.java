package com.bignerdranch.service;

import com.bignerdranch.exception.Type;
import com.bignerdranch.exception.NotFoundException;
import com.bignerdranch.model.Expertise;
import com.bignerdranch.respository.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class ExpertiseService {

    @Autowired
    private ExpertiseRepository repo;

    public Expertise readOne(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(Type.EXPERTISE, id));
    }

    public List<Expertise> readAll(){
        return repo.findAll();
    }

    public Expertise create(Expertise newValue){
        return repo.save(newValue);
    }

    public Expertise update(Expertise toUpdate, Long id){
        return repo.findById(id)
                .map(obj -> {
                    obj.setName(toUpdate.getName());
                    return repo.save(obj);
                })
                .orElseThrow(() -> new NotFoundException(Type.EXPERTISE, id));
    }

    public void delete(Long id){
        repo.findById(id)
                .orElseThrow(() -> new NotFoundException(Type.EXPERTISE, id));
        repo.deleteById(id);
    }

    public Expertise findOrCreate(String name){
        if(name == null) return null;
        Expertise test = repo.findByName(name)
                .orElseGet(() -> {
                    Expertise expertise = new Expertise(name);
                    return repo.save(expertise);
                });
        return test;
    }

}
