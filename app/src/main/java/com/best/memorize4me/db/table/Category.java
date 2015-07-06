package com.best.memorize4me.db.table;

import android.provider.BaseColumns;

import java.sql.Date;

/**
 * Created by Acer on 6.7.2015.
 */
public final class Category {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public Category() {}

    /* Inner class that defines the table contents */
    public static abstract class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date"; // sql date?

    }
}