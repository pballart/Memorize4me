package com.best.memorize4me.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.best.memorize4me.MemorizeForMeApplication;
import com.best.memorize4me.db.interfaces.AppInterface;
import com.best.memorize4me.db.model.SearchItem;
import com.best.memorize4me.db.table.Category;

import java.util.ArrayList;

/**
 * Created by konstantin.bogdanov on 07.07.2015.
 */
public class StorageFacade implements AppInterface{
    private static StorageFacade instance;

    private StorageFacade() {

    }

    private static SQLiteDatabase getDatabase() {
        return MemorizeForMeApplication.getDatabase(MemorizeForMeApplication.getContext());
    }

    public static StorageFacade getInstance() {
        if (instance == null) {
            instance = new StorageFacade();
        }
        return instance;
    }

    @Override
    public ArrayList<com.best.memorize4me.db.model.Category> getCategories() {
        ArrayList<com.best.memorize4me.db.model.Category> categoryList = new ArrayList<com.best.memorize4me.db.model.Category>();
        String[] projection = {
                Category.CategoryEntry.COLUMN_ID,
                Category.CategoryEntry.COLUMN_TITLE,
                Category.CategoryEntry.COLUMN_DATE
        };
        Cursor cursor = getDatabase().query(
                Category.CategoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                com.best.memorize4me.db.model.Category category = new com.best.memorize4me.db.model.Category(
                        Long.parseLong(cursor.getString(0)),
                        cursor.getString(1),
                        Long.parseLong(cursor.getString(2))
                );
                categoryList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categoryList;
    }

    @Override
    public ArrayList<SearchItem> getSearchItemByCategory(long categoryId) {
        return null;
    }

    @Override
    public void createCategory(com.best.memorize4me.db.model.Category category) {
        ContentValues values = new ContentValues();
        values.put(Category.CategoryEntry.COLUMN_TITLE, category.title);
        values.put(Category.CategoryEntry.COLUMN_DATE, category.date);
        getDatabase().insert(
                Category.CategoryEntry.TABLE_NAME,
                null,
                values
        );
    }

    @Override
    public void removeCategory(long categoryId) {
        getDatabase().delete(
                Category.CategoryEntry.TABLE_NAME,
                Category.CategoryEntry.COLUMN_ID + " = ?",
                new String[] { String.valueOf(categoryId) }
        );
    }

    @Override
    public void updateCategory(com.best.memorize4me.db.model.Category category) {
        ContentValues values = new ContentValues();
        values.put(Category.CategoryEntry.COLUMN_TITLE, category.title);
        values.put(Category.CategoryEntry.COLUMN_DATE, category.date);
        getDatabase().update(
                Category.CategoryEntry.TABLE_NAME,
                values,
                Category.CategoryEntry.COLUMN_ID + " = ?",
                new String[] { String.valueOf(category.id) }
        );
    }


    @Override
    public void createSearchItem(SearchItem searchItem, com.best.memorize4me.db.model.Category category) {
        ContentValues values = new ContentValues();
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_CATEGORY_ID, category.id);
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_NAME, searchItem.contact.firstName + " " + searchItem.contact.lastName );
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_DATE, searchItem.date);
//        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_LOCATION, String.valueOf(searchItem.location));
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_EMAIL, searchItem.contact.email);
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_PHONE_NUMBER, searchItem.contact.phoneNumber);
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_DATE, searchItem.date);
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_DESCRIPTION, searchItem.description);
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_PRICE, searchItem.price);
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_RATE, searchItem.rate);
        values.put(com.best.memorize4me.db.table.SearchItem.SearchItemEntry.COLUMN_TITLE, searchItem.title);
        getDatabase().insert(
                com.best.memorize4me.db.table.SearchItem.SearchItemEntry.TABLE_NAME,
                null,
                values
        );
    }

    @Override
    public void removeSearchItem(long searchItemId) {

    }

    @Override
    public void updateSearchItem(SearchItem searchItem) {

    }
}
