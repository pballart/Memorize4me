package com.best.memorize4me.db.model;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by konstantin.bogdanov on 06.07.2015.
 */
public class SearchItem {
    public long id;
    public long categoryId;
    public String title;
    public long date;
    public float price;
    public float rate;
    public Contact contact;
    public String description;
    public Location location;
    public String multilineDescription;
    public ArrayList<SearchItemPhoto> imageUrls;

    public SearchItem() {}

    public SearchItem(long id, long categoryId, String title,
                      Long date, float price, float rate,
                      Contact contact, String description, Location location,
                      String multilineDescription) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.date = date;
        this.price = price;
        this.rate = rate;
        this.contact = contact;
        this.description = description;
        this.location = location;
        this.multilineDescription = multilineDescription;
        this.imageUrls = null;
        //this.imageUrls = imageUrls;
        /*
        this.imageUrls = new ArrayList<SearchItemPhoto>();
        for (SearchItemPhoto imageUrl : imageUrls) {
            this.imageUrls.add(imageUrl);
        }
        */
    }

    public SearchItem(long id, long categoryId, String title,
                      Long date, float price, float rate,
                      Contact contact, String description, Location location,
                      String multilineDescription, ArrayList<SearchItemPhoto> imageUrls) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.date = date;
        this.price = price;
        this.rate = rate;
        this.contact = contact;
        this.description = description;
        this.location = location;
        this.multilineDescription = multilineDescription;
        this.imageUrls = null;
        //this.imageUrls = imageUrls;
        /*
        this.imageUrls = new ArrayList<SearchItemPhoto>();
        for (SearchItemPhoto imageUrl : imageUrls) {
            this.imageUrls.add(imageUrl);
        }
        */
    }

    public SearchItem(long id, long categoryId, String title,
                      Long date, float price, float rate,
                      String contactName, String contactPhoneNumber, String contactEmail,
                      String description, Location location, String multilineDescription,
                      ArrayList<SearchItemPhoto> imageUrls) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.date = date;
        this.price = price;
        this.rate = rate;
        this.contact = new Contact(contactName, contactPhoneNumber, contactEmail);
        this.description = description;
        this.location = location;
        this.multilineDescription = multilineDescription;
        this.imageUrls = null;
        //this.imageUrls = imageUrls;
        /*
        this.imageUrls = new ArrayList<SearchItemPhoto>();
        for (SearchItemPhoto imageUrl : imageUrls) {
            this.imageUrls.add(imageUrl);
        }
        */
    }
}
