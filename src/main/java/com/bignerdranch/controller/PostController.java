package com.bignerdranch.controller;


import com.bignerdranch.model.Post;
import com.bignerdranch.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostRepository repo;

    @GetMapping("/posts/{id}")
    public EntityModel<Post> readOne(@PathVariable Long id){
        return null;
    }

    @GetMapping("/posts")
    public CollectionModel<EntityModel<Post>> readAll(){
        return null;
    }

    @PostMapping("/posts")
    public ResponseEntity create(@RequestBody Post newValue){
        return null;
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity update(@RequestBody Post toUpdate, @PathVariable Long id){
        return null;
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return null;
    }
    
}
