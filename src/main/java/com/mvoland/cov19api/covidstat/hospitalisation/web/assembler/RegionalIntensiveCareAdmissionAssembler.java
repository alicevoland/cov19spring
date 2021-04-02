package com.mvoland.cov19api.covidstat.hospitalisation.web.assembler;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.covidstat.hospitalisation.web.HospitalisationController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegionalIntensiveCareAdmissionAssembler implements RepresentationModelAssembler<RegionalIntensiveCareAdmission, EntityModel<RegionalIntensiveCareAdmission>> {
    @Override
    public EntityModel<RegionalIntensiveCareAdmission> toModel(RegionalIntensiveCareAdmission entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(HospitalisationController.class).oneRegionalIntensiveCareAdmissionById(entity.getId())).withSelfRel(),
                linkTo(methodOn(HospitalisationController.class).allRegionalIntensiveCareAdmissions()).withRel("all"));
    }
}
