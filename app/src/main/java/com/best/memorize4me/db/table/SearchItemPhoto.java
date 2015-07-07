package com.best.memorize4me.db.table;

import android.provider.BaseColumns;

/**
 * Created by Acer on 7.7.2015.
 */
public final class SearchItemPhoto {

    public SearchItemPhoto() {}

    public static abstract class SearchItemPhotoEntry implements BaseColumns {
        public static final String TABLE_NAME = "search_item_photo";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_SEARCH_ITEM_ID = "search_item_id";
    }
}
