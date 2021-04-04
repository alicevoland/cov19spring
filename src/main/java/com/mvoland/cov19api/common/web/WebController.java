package com.mvoland.cov19api.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/")
public class WebController {


    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }


}
