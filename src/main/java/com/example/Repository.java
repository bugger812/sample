package com.example;

import com.example.Objects.ProviderObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f107 on 24/04/2017.
 */

@Component
public class Repository {

    private static List<ProviderObject> providerObjectList;

    private Repository() {
        providerObjectList = new ArrayList<>();
        ProviderObject p1 = new ProviderObject(1, "Moshe", "Doctor", 123);
        ProviderObject p2 = new ProviderObject(2, "Shai", "Programmer", 456);
        providerObjectList.add(p1);
        providerObjectList.add(p2);
    }

    public static List<ProviderObject> getProviderObjectList() {
        return providerObjectList;
    }

}
