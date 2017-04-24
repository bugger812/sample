package com.example.Objects;

/**
 * Created by f107 on 24/04/2017.
 */
public class ProviderObject {

    private int id;
    private String name;
    private String profession;
    private long phoneNumber;


    public ProviderObject(int id, String name, String profession, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}


