package com.best.memorize4me.db.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by konstantin.bogdanov on 06.07.2015.
 */
public class Category implements Serializable {
    public long id;
    public String title;
    public long date;

    public Category() {}

    public Category(long id, String title, long date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Date getDate() {
        Date date = new Date(this.date);
        return date;
    }
}
