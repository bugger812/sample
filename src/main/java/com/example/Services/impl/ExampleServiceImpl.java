package com.example.Services.impl;

import com.example.Persist;
import com.example.Services.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sigal on 5/16/2016.
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private Persist persist;

    public boolean checkCredentials (String userName, String password) {
        if (userName != null && password != null) {
            return userName.equals("my_name") && password.equals("my_password");
        }
        return false;
    }

}
