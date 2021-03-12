package com.mvoland.cov19api.web;

import com.mvoland.cov19api.business.domain.RegionIntensiveCare;
import com.mvoland.cov19api.business.service.RegionIntensiveCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class WebController {

    private final RegionIntensiveCareService regionIntensiveCareService;

    @Autowired
    public WebController(RegionIntensiveCareService regionIntensiveCareService) {
        this.regionIntensiveCareService = regionIntensiveCareService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/regions")
    public String regions(Model model) {
        List<RegionIntensiveCare> regions = this.regionIntensiveCareService.getAllRegionIntensiveCares();
        model.addAttribute("regions", regions);
        return "regions";
    }

}
