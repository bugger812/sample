 package com.example.Controllers;

import com.example.Objects.ProviderObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by f107 on 24/04/2017.
 */
@Controller
public class AviadController {

    @RequestMapping("/provider")
    public String about(Model model) {
        ProviderObject providerObject = new ProviderObject(1, "Moshe", "Doctor", 0501234567, 4500, 4.5 );
        model.addAttribute("provider",providerObject);


        return "tmpl_provider";
    }
}
