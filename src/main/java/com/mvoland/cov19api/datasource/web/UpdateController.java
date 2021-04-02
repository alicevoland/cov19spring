package com.mvoland.cov19api.datasource.web;

import com.mvoland.cov19api.datasource.common.UpdateRequest;
import com.mvoland.cov19api.datasource.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/update")
public class UpdateController {


    private final UpdateService updateService;

    @Autowired
    public UpdateController(
            UpdateService updateService
    ) {
        this.updateService = updateService;
    }

    @GetMapping("since")
    public EntityModel<UpdateRequest> requestUpdateDays(
            @RequestParam Integer days
    ) {
        UpdateRequest result = updateService.requestUpdateDays(days);
        return EntityModel.of(result);
    }

    @GetMapping("full")
    public EntityModel<UpdateRequest> requestFullUpdate(
    ) {
        UpdateRequest result = updateService.requestFullUpdate();
        return EntityModel.of(result);
    }

}
