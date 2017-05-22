package com.example.Controllers;

import com.example.Utils.CalculationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ExampleController {

    @Autowired
    private CalculationUtils calculationUtils;




    @PostConstruct
    public void printInfo() {
        System.out.println("The object has constructed and this is post construct");

    }

    @RequestMapping("/about")
    public String about() {
        int result = calculationUtils.add(4, 5);
        return "tmpl_about";
    }


    @RequestMapping("/shai")
    public String shai() {
        return "tmpl_shai";
    }

}
