package com.best.memorize4me.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.best.memorize4me.db.table.SearchItemPhoto;
import com.best.memorize4me.db.table.Category;
import com.best.memorize4me.db.table.SearchItem;

import static com.best.memorize4me.db.table.Category.*;
import static com.best.memorize4me.db.table.SearchItem.*;

/**
 * Created by Acer on 6.7.2015.
 */
/*
public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String INTEGER_TYPE = " INTEGER";

//    private static final String TEXT_TYPE = " TEXT";
//    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_CATEGORIES =
            "CREATE TABLE" + CategoryEntry.TABLE_NAME + " (" +
                    CategoryEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    CategoryEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    CategoryEntry.COLUMN_DATE + INTEGER_TYPE +
                    " )";

    private static final  String SQL_CREATE_SEARCH_ITEMS =
            "CREATE TABLE" + SearchItemEntry.TABLE_NAME + " (" +
                    SearchItemEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    SearchItemEntry.COLUMN_CATEGORY_ID +
            ;

/*
    private static final String SQL_DELETE_CATEGORIES =
            "DROP TABLE IF EXISTS " + Category.CategoryEntry.TABLE_NAME;

    private static final String SQL_DELETE_SEARCH_ITEMS =
            "DROP TABLE IF EXISTS " + SearchItem.SearchItemEntry.TABLE_NAME;

    private static final String SQL_DELETE_SEARCH_ITEM_PHOTOS =
            "DROP TABLE IF EXISTS " + SearchItemPhoto.SearchItemPhotoEntry.TABLE_NAME;
*/

    public DbHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        super(context, "dsds", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
*/
