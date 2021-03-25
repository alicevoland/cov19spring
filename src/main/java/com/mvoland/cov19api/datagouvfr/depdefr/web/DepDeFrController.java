package com.mvoland.cov19api.datagouvfr.depdefr.web;

import com.mvoland.cov19api.datagouvfr.depdefr.data.DepartementDeFranceService;
import com.mvoland.cov19api.datagouvfr.depdefr.service.DepDeFrUpdateService;
import com.mvoland.cov19api.datagouvfr.hospdata.service.HospDataUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/update/depdefr")
public class DepDeFrController {


    private final DepartementDeFranceService departementDeFranceService;

    @Autowired
    public DepDeFrController(
            DepartementDeFranceService departementDeFranceService
    ) {
        this.departementDeFranceService = departementDeFranceService;
    }

    @GetMapping("sources")
    public List<String> getAllSourceNames() {
        return Arrays.asList("DepartementDeFrance");
    }


    @GetMapping("DepartementDeFrance")
    public Boolean requestUpdate() {
        return departementDeFranceService.requestFullUpdate();
    }

}
