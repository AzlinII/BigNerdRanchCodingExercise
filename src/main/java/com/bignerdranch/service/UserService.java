package com.bignerdranch.service;

import com.bignerdranch.exception.Type;
import com.bignerdranch.exception.NotFoundException;
import com.bignerdranch.model.Expertise;
import com.bignerdranch.model.User;
import com.bignerdranch.object.UserUpdateRequest;
import com.bignerdranch.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private ExpertiseService expertiseService;

    public User readOne(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(Type.USER, id));
    }

    public List<User> readAll(){
        return repo.findAll();
    }

    public User create(UserUpdateRequest request){
        Expertise expertise = expertiseService.findOrCreate(request.getExpertise());
        User newUser = new User(request.getEmail(), expertise);
        return repo.save(newUser);
    }

    public User update(UserUpdateRequest request, Long id){
        return repo.findById(id)
                .map(obj -> {
                    if(request.getEmail() != null) {
                        obj.setEmail(request.getEmail());
                    }
                    if(request.getExpertise() != null){
                        Expertise expertise = expertiseService.findOrCreate(request.getExpertise());
                        obj.setExpertise(expertise);
                    }
                    return repo.save(obj);
                })
                .orElseThrow(() -> new NotFoundException(Type.USER, id));
    }

    public void delete(Long id){
        repo.findById(id)
                .orElseThrow(() -> new NotFoundException(Type.USER, id));
        repo.deleteById(id);
    }

}
