package com.mvoland.cov19api.covidstat.locality.web.assembler;

import com.mvoland.cov19api.covidstat.locality.web.api.LocalityApiController;
import com.mvoland.cov19api.covidstat.locality.data.entity.Department;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentModelAssembler implements RepresentationModelAssembler<Department, EntityModel<Department>> {
    @Override public EntityModel<Department> toModel(Department entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(LocalityApiController.class).oneRegionById(entity.getId())).withSelfRel(),
                linkTo(methodOn(LocalityApiController.class).oneRegionByCode(entity.getDepartmentCode())).withRel("selfByCode"),
                linkTo(methodOn(LocalityApiController.class).allRegions()).withRel("all"));
    }
}
