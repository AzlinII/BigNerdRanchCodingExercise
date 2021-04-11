package com.bignerdranch.service;

import com.bignerdranch.exception.NotFoundException;
import com.bignerdranch.exception.Type;
import com.bignerdranch.model.Post;
import com.bignerdranch.model.User;
import com.bignerdranch.object.PostUpdateRequest;
import com.bignerdranch.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    @Autowired
    private UserService userService;

    public Post readOne(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(Type.POST, id));
    }

    public List<Post> readMultiple(Long user_id){
        if(user_id != null){
            return repo.findPostsByUserId(user_id);
        }
        return repo.findAll();
    }

    public Post create(PostUpdateRequest request){
        // user service will throw error if invalid user
        User user = userService.readOne(request.getUser_id());
        Post post = new Post(request.getTitle(), request.getBody(), user);
        return repo.save(post);
    }

    public Post update(PostUpdateRequest request, Long id){
        return repo.findById(id)
                .map(obj -> {
                    if(request.getTitle() != null){
                        obj.setTitle(request.getTitle());
                    }
                    if(request.getBody() != null){
                        obj.setBody(request.getBody());
                    }
                    return repo.save(obj);
                })
                .orElseThrow(() -> new NotFoundException(Type.POST, id));
    }

    public void delete(Long id){
        repo.findById(id)
                .orElseThrow(() -> new NotFoundException(Type.POST, id));
        repo.deleteById(id);
    }

}
