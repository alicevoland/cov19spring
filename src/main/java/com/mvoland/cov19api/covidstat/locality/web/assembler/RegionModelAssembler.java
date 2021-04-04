package com.mvoland.cov19api.covidstat.locality.web.assembler;

import com.mvoland.cov19api.covidstat.locality.web.api.LocalityApiController;
import com.mvoland.cov19api.covidstat.locality.data.entity.Region;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegionModelAssembler implements RepresentationModelAssembler<Region, EntityModel<Region>> {
    @Override public EntityModel<Region> toModel(Region entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(LocalityApiController.class).oneRegionById(entity.getId())).withSelfRel(),
                linkTo(methodOn(LocalityApiController.class).oneRegionByCode(entity.getRegionCode())).withRel("selfByCode"),
                linkTo(methodOn(LocalityApiController.class).allRegions()).withRel("all"));
    }
}
