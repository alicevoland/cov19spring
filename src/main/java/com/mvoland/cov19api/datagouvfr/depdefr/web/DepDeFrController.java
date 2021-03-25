package com.mvoland.cov19api.datagouvfr.depdefr.web;

import com.mvoland.cov19api.datagouvfr.depdefr.service.DepDeFrUpdateService;
import com.mvoland.cov19api.datagouvfr.hospdata.service.HospDataUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/depdefr")
public class DepDeFrController {

    private final DepDeFrUpdateService depDeFrUpdateService;

    @Autowired
    public DepDeFrController(
            DepDeFrUpdateService depDeFrUpdateService
    ) {
        this.depDeFrUpdateService = depDeFrUpdateService;
    }

    @GetMapping("update")
    public Boolean requestUpdate(
            @RequestParam(required = false) Integer days) {
        return depDeFrUpdateService.requestUpdateByDays("DepartementDeFrance", days);
    }

}
