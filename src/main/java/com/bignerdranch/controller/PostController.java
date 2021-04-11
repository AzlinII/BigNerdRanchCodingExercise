package com.bignerdranch.controller;

import com.bignerdranch.model.Post;
import com.bignerdranch.object.PostUpdateRequest;
import com.bignerdranch.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService service;


    @GetMapping("/posts/{id}")
    public Post readOne(@PathVariable Long id){
        return service.readOne(id);
    }

    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(required = false) Long user_id){
        return service.readMultiple(user_id);
    }

    @PostMapping("/posts")
    public Post create(@RequestBody PostUpdateRequest request){
        return service.create(request);
    }

    @PutMapping("/posts/{id}")
    public Post update(@RequestBody PostUpdateRequest request, @PathVariable Long id){
        return service.update(request, id);
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
