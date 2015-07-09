package com.best.memorize4me.db.model;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by konstantin.bogdanov on 06.07.2015.
 */
public class SearchItem implements Parcelable {
    public long id;
    public long categoryId;
    public String title;
    public long date;
    public float price;
    public float rate;
    public Contact contact;
    public String description;
    public Location location;
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
                      Contact contact, String description, Location location, ArrayList<SearchItemPhoto> imageUrls) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.date = date;
        this.price = price;
        this.rate = rate;
        this.contact = contact;
        this.description = description;
        this.location = location;
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
                      String description, Location location,
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
        this.imageUrls = null;
        //this.imageUrls = imageUrls;
        /*
        this.imageUrls = new ArrayList<SearchItemPhoto>();
        for (SearchItemPhoto imageUrl : imageUrls) {
            this.imageUrls.add(imageUrl);
        }
        */
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(categoryId);
        dest.writeString(title);
        dest.writeLong(date);
        dest.writeFloat(price);
        dest.writeFloat(rate);
        dest.writeString(contact.name);
        dest.writeString(contact.phoneNumber);
        dest.writeString(contact.email);
        dest.writeString(description);
    }

    public static final Parcelable.Creator<SearchItem> CREATOR = new Creator<SearchItem>() {

        @Override
        public SearchItem createFromParcel(Parcel source) {
            SearchItem searchItem = new SearchItem();
            searchItem.id = source.readLong();
            searchItem.categoryId = source.readLong();
            searchItem.title = source.readString();
            searchItem.date = source.readLong();
            searchItem.price = source.readFloat();
            searchItem.rate = source.readFloat();
            String contactName = source.readString();
            String contactPhoneNumber = source.readString();
            String contactEmail = source.readString();
            searchItem.contact = new Contact(contactName, contactPhoneNumber, contactEmail);
            searchItem.description = source.readString();
            return searchItem;
        }

        @Override
        public SearchItem[] newArray(int size) {
            return new SearchItem[size];
        }

    };
}
