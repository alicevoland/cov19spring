package com.mvoland.cov19api.covidstat.locality.web;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentModelAssembler implements RepresentationModelAssembler<Department, EntityModel<Department>> {
    @Override public EntityModel<Department> toModel(Department entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(LocalityController.class).oneRegionById(entity.getId())).withSelfRel(),
                linkTo(methodOn(LocalityController.class).oneRegionByCode(entity.getDepartmentCode())).withRel("selfByCode"),
                linkTo(methodOn(LocalityController.class).allRegions()).withRel("all"));
    }
}
