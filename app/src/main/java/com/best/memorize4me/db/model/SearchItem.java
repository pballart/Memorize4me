package com.best.memorize4me.db.model;

import android.location.Location;

import com.best.memorize4me.db.Contact;

import java.util.ArrayList;

/**
 * Created by konstantin.bogdanov on 06.07.2015.
 */
public class SearchItem {
    long id;
    long categoryId;
    String title;
    Long date;
    float price;
    int rate;
    Contact contact;
    String description;
    Location location;
    ArrayList<SearchItemPhoto> imageUrls;
}
