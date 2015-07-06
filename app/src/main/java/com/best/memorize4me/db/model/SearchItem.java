package com.best.memorize4me.db.model;

import android.location.Location;

import com.best.memorize4me.db.Contact;

import java.util.ArrayList;

/**
 * Created by konstantin.bogdanov on 06.07.2015.
 */
public class SearchItem {
    public long id;
    public long categoryId;
    public String title;
    public Long date;
    public float price;
    public int rate;
    public Contact contact;
    public String description;
    public  Location location;
    public ArrayList<SearchItemPhoto> imageUrls;
}
