package com.example.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by f107 on 08/05/2017.
 */

@Controller
public class SasonController {


    @RequestMapping("/sason")
    public String sason()  {
        return "tmpl_sason";

    }



    }



