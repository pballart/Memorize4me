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
    public  Location location;
    public String multilineDescription;
    public ArrayList<SearchItemPhoto> imageUrls;
}
