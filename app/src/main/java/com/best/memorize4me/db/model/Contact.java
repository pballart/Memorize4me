package com.best.memorize4me.db.model;

/**
 * Created by konstantin.bogdanov on 06.07.2015.
 */
public class Contact {
    public String name;
    public String phoneNumber;
    public String email;

    public Contact() {}

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Contact(Contact that) {
        this.name = that.name;
        this.phoneNumber = that.phoneNumber;
        this.email = that.email;
    }
}
