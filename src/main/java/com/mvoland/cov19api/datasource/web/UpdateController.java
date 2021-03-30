package com.mvoland.cov19api.datasource.web;

import com.mvoland.cov19api.datasource.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Request a partial update with recent data
     *
     * @param days Update with new data from the past days
     * @return True if update request accepted
     */
    @GetMapping("since")
    public Boolean requestUpdateDays(
            @RequestParam Integer days
    ) {
        return updateService.requestUpdateDays(days);
    }

    /**
     * Request a full update
     *
     * @return True if update request accepted
     */
    @GetMapping("full")
    public Boolean requestFullUpdate(
    ) {
        return updateService.requestFullUpdate();
    }

}
