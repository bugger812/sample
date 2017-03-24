package com.example.Services;


import javax.transaction.Transactional;

/**
 * Created by Sigal on 5/20/2016.
 */
@Transactional
public interface ExampleService {

    boolean checkCredentials (String userName, String password);

}

