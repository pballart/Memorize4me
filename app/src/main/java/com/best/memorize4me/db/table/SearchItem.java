package com.best.memorize4me.db.table;

import android.provider.BaseColumns;

/**
 * Created by Acer on 6.7.2015.
 */
public final class SearchItem {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public SearchItem() {}

    /* Inner class that defines the table contents */
    public static abstract class SearchItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "search_item";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_RATE = "rate";
        public static final String COLUMN_NAME = "contact";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_LOCATION = "location";

    }
}