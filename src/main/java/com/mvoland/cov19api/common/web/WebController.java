package com.mvoland.cov19api.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/")
public class WebController {


    @RequestMapping("/")
    public ModelAndView index(ModelMap model) {
        return new ModelAndView("redirect:/docs/index.html", model);
    }


}
