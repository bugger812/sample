package com.example.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ExampleController {

    @RequestMapping("/about")
    public String about() {
         return "tmpl_about";
    }


    @RequestMapping("/shai")
    public String shai() {
        return "tmpl_shai";
    }

}
