package com.mvoland.cov19api.datagouvfr.hospdata.web;

import com.mvoland.cov19api.datagouvfr.hospdata.update.HospDataUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/update")
public class HospDataUpdateController {

    private final HospDataUpdateService hospDataUpdateService;

    @Autowired
    public HospDataUpdateController(
            HospDataUpdateService hospDataUpdateService
    ) {
        this.hospDataUpdateService = hospDataUpdateService;
    }

    @GetMapping("request/{dataSourceName}")
    public Boolean requestUpdate(@PathVariable String dataSourceName) {
        return hospDataUpdateService.requestUpdate(dataSourceName);
    }

    @GetMapping("sources")
    public List<String> getAllSourceNames() {
        return hospDataUpdateService.getSourceNames();
    }

}
