package com.bignerdranch.assembler;

import com.bignerdranch.controller.ExpertiseController;
import com.bignerdranch.controller.UserController;
import com.bignerdranch.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/* Used to create the related links in the response for UserController */
@Component
public class UserAssembler implements
        RepresentationModelAssembler<User, EntityModel<User>> {

    /* Creates an entity with link to one() method and all() method of controller */
    @Override
    public EntityModel<User> toModel(User entity) {
        WebMvcLinkBuilder selfLink = linkTo(methodOn(UserController.class).readOne(entity.getId()));
        WebMvcLinkBuilder allLink = linkTo(methodOn(UserController.class).readAll());
        return EntityModel.of(entity, selfLink.withSelfRel(), allLink.withRel("users"));
    }

    /* Creates a response entity with links to the newly saved entity */
    public ResponseEntity toResponseEntity(User entity){
        EntityModel<User> entityModel = this.toModel(entity);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
