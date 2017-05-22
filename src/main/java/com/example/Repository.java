package com.example;

import com.example.Objects.ProviderObject;
import com.example.Objects.UserObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f107 on 24/04/2017.
 */

@Component
public class Repository {

    private static List<ProviderObject> providerObjectList;

    private static List<UserObject> userObjects;

    private Repository() {
        providerObjectList = new ArrayList<>();
        ProviderObject p1 = new ProviderObject(1, "Moshe", "Doctor", 123, 100, 56);
        ProviderObject p2 = new ProviderObject(2, "Shai", "Programmer", 456, 150, 12);
        providerObjectList.add(p1);
        providerObjectList.add(p2);

        userObjects= new ArrayList<>();
        UserObject userObject = new UserObject();
        userObject.setAddress("Ashkelon");
        userObject.setPhone("050-4444444");
        userObject.setUsername("Sason");
        userObjects.add(userObject);

    }

    public static List<ProviderObject> getProviderObjectList() {
        return providerObjectList;
    }

    public static List<UserObject> getAllUsers () {
        return userObjects;
    }







}
