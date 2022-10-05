package dev.decagon.entity;

import dev.decagon.enums.Gender;

public abstract class User {
    private String name;
    private String email;
    private Gender gender;

    public User() {
    }

    public User( String name, String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
