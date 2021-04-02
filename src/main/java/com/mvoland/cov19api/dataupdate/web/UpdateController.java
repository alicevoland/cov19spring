package com.mvoland.cov19api.dataupdate.web;

import com.mvoland.cov19api.common.ParsingUtils;
import com.mvoland.cov19api.dataupdate.data.UpdateRequest;
import com.mvoland.cov19api.dataupdate.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/update")
public class UpdateController {


    private final UpdateService updateService;

    @Autowired
    public UpdateController(
            UpdateService updateService
    ) {
        this.updateService = updateService;
    }

    @GetMapping("days")
    public EntityModel<UpdateRequest> requestUpdateDays(
            @RequestParam Integer days
    ) {
        LocalDate noticeDateBegin = LocalDate.now().minusDays(days);
        UpdateRequest result = updateService.requestUpdateSince(noticeDateBegin);
        return EntityModel.of(result);
    }

    @GetMapping("date")
    public EntityModel<UpdateRequest> requestUpdateDays(
            @RequestParam String date
    ) {
        LocalDate noticeDateBegin = ParsingUtils.parseDateOrThrow(date,
                () -> new CannotUpdateException("could not parse date"));
        UpdateRequest result = updateService.requestUpdateSince(noticeDateBegin);
        return EntityModel.of(result);
    }

    @GetMapping("auto")
    public EntityModel<UpdateRequest> requestAutoUpdate() {
        UpdateRequest result = updateService.requestAutoUpdate();
        return EntityModel.of(result);
    }

    @GetMapping("full")
    public EntityModel<UpdateRequest> requestFullUpdate(
    ) {
        UpdateRequest result = updateService.requestFullUpdate();
        return EntityModel.of(result);
    }

}
