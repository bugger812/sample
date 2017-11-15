package com.example.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pod-6 on 22/05/2017.
 */


@Controller
public class DashboardController {

    @RequestMapping("/")
    public String dashboard() {
        int i = 0;
        return "tmpl_dashboard";
    }




}
