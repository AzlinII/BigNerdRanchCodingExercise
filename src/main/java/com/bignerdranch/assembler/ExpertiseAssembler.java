package com.bignerdranch.assembler;

import com.bignerdranch.controller.ExpertiseController;
import com.bignerdranch.model.Expertise;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/* Used to create the related links in the response for ExpertiseController */
@Component
public class ExpertiseAssembler implements
        RepresentationModelAssembler<Expertise, EntityModel<Expertise>> {

    /* Creates an entity with link to one() method and all() method of controller */
    @Override
    public EntityModel<Expertise> toModel(Expertise entity) {
        WebMvcLinkBuilder selfLink = linkTo(methodOn(ExpertiseController.class).readOne(entity.getId()));
        WebMvcLinkBuilder allLink = linkTo(methodOn(ExpertiseController.class).readAll());
        return EntityModel.of(entity, selfLink.withSelfRel(), allLink.withRel("expertises"));
    }

    /* Creates a response entity with links to the newly saved entity */
    public ResponseEntity toResponseEntity(Expertise entity){
        EntityModel<Expertise> entityModel = this.toModel(entity);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
