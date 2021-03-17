package com.mvoland.cov19api.web;

import com.mvoland.cov19api.business.domain.RegionIntensiveCare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class WebController {


    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }


}
