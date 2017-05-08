package com.example.Controllers;

import com.example.Objects.ProviderObject;
import com.example.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by f107 on 08/05/2017.
 */

@Controller

public class EliorController {


    @RequestMapping("/best-providers")
    public String ProviderObject(Model model) {
        List<ProviderObject> providerObjects = Repository.getProviderObjectList();
        ProviderObject p1 = providerObjects.get(0);
        model.addAttribute("name", p1.getName());
        model.addAttribute("allProviders", providerObjects);
        return "tmpl_best_providers";
    }
}





