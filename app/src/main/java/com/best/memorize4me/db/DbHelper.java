package com.best.memorize4me.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.best.memorize4me.db.table.SearchItemPhoto;
import com.best.memorize4me.db.table.Category;
import com.best.memorize4me.db.table.SearchItem;

import static com.best.memorize4me.db.table.Category.*;
import static com.best.memorize4me.db.table.SearchItem.*;
import static com.best.memorize4me.db.table.SearchItemPhoto.*;


public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATE_TYPE = " DATE";

    private static final String SQL_CREATE_CATEGORIES =
            "CREATE TABLE " + CategoryEntry.TABLE_NAME + " (" +
                    CategoryEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    CategoryEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    CategoryEntry.COLUMN_DATE + INTEGER_TYPE +
                    " )"
            ;

    private static final  String SQL_CREATE_SEARCH_ITEMS =
            "CREATE TABLE " + SearchItemEntry.TABLE_NAME + " (" +
                    SearchItemEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    SearchItemEntry.COLUMN_CATEGORY_ID + INTEGER_TYPE +
                    SearchItemEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    SearchItemEntry.COLUMN_DATE + DATE_TYPE + COMMA_SEP +
                    SearchItemEntry.COLUMN_PRICE + INTEGER_TYPE + COMMA_SEP +
                    SearchItemEntry.COLUMN_RATE + INTEGER_TYPE + COMMA_SEP +
                    SearchItemEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    SearchItemEntry.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
                    SearchItemEntry.COLUMN_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP +
                    SearchItemEntry.COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    SearchItemEntry.COLUMN_LOCATION + TEXT_TYPE +
                    " )"
            ;

    private static final String SQL_CREATE_SEARCH_ITEM_PHOTOS =
            "CREATE TABLE " + SearchItemPhotoEntry.TABLE_NAME + " (" +
                    SearchItemPhotoEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    SearchItemPhotoEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    SearchItemPhotoEntry.COLUMN_URL + TEXT_TYPE + COMMA_SEP +
                    SearchItemPhotoEntry.COLUMN_SEARCH_ITEM_ID + INTEGER_TYPE +
                    " )"
            ;

    private static final String SQL_DELETE_CATEGORIES =
            "DROP TABLE IF EXISTS " + Category.CategoryEntry.TABLE_NAME;

    private static final String SQL_DELETE_SEARCH_ITEMS =
            "DROP TABLE IF EXISTS " + SearchItem.SearchItemEntry.TABLE_NAME;

    private static final String SQL_DELETE_SEARCH_ITEM_PHOTOS =
            "DROP TABLE IF EXISTS " + SearchItemPhotoEntry.TABLE_NAME;


    public DbHelper(Context context) {
        super(context, "memorize4me", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CATEGORIES);
        db.execSQL(SQL_CREATE_SEARCH_ITEMS);
        db.execSQL(SQL_CREATE_SEARCH_ITEM_PHOTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SEARCH_ITEM_PHOTOS);
        db.execSQL(SQL_DELETE_SEARCH_ITEMS);
        db.execSQL(SQL_DELETE_CATEGORIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}