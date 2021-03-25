package com.mvoland.cov19api.datagouvfr.hospdata.web;

import com.mvoland.cov19api.datagouvfr.hospdata.service.HospDataUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospdata")
public class HospDataUpdateController {

    private final HospDataUpdateService hospDataUpdateService;

    @Autowired
    public HospDataUpdateController(
            HospDataUpdateService hospDataUpdateService
    ) {
        this.hospDataUpdateService = hospDataUpdateService;
    }

    @GetMapping("update/{dataSourceName}")
    public Boolean requestUpdate(
            @PathVariable String dataSourceName,
            @RequestParam(required = false) Integer days) {
        return hospDataUpdateService.requestUpdateByDays(dataSourceName, days);
    }

    @GetMapping("sources")
    public List<String> getAllSourceNames() {
        return hospDataUpdateService.getSourceNames();
    }

}
