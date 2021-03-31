package com.mvoland.cov19api.covidstat.locality.web;

import com.mvoland.cov19api.covidstat.locality.data.Region;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegionModelAssembler implements RepresentationModelAssembler<Region, EntityModel<Region>> {
    @Override public EntityModel<Region> toModel(Region entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(LocalityController.class).oneRegionById(entity.getId())).withSelfRel(),
                linkTo(methodOn(LocalityController.class).oneRegionByCode(entity.getRegionCode())).withRel("selfByCode"),
                linkTo(methodOn(LocalityController.class).allRegions()).withRel("regions"));
    }
}
