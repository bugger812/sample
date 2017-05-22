package com.example.Controllers;

import com.example.Objects.ProviderObject;
import com.example.Objects.UserObject;
import com.example.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by f107 on 08/05/2017.
 */

@Controller
public class  SasonController {


    @RequestMapping("/sason")
    public String sason(Model model) {

        List<UserObject> users = Repository.getAllUsers();
        UserObject user = users.get(0);


        model.addAttribute("userObject", user);

        return "tmpl_sason";

    }


}



