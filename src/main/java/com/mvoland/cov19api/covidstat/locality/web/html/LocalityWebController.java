package com.mvoland.cov19api.covidstat.locality.web.html;

import com.mvoland.cov19api.covidstat.locality.model.RegionWrapper;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/locality")
public class LocalityWebController {
    private final LocalityService localityService;

    @Autowired
    public LocalityWebController(
            LocalityService localityService
    ) {
        this.localityService = localityService;
    }

    @RequestMapping("/all")
    public String localities(Model model) {
        model.addAttribute(
                "regionWrapperList",
                RegionWrapper.createRegionWrapperList(localityService.findAllDepartments()));
        return "locality/all";
    }


}
