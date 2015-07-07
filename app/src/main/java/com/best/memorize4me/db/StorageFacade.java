package com.best.memorize4me.db;

import android.database.sqlite.SQLiteDatabase;

import com.best.memorize4me.MemorizeForMeApplication;
import com.best.memorize4me.db.interfaces.AppInterface;
import com.best.memorize4me.db.model.Category;
import com.best.memorize4me.db.model.SearchItem;

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

    public synchronized StorageFacade getInstance() {
        if (instance == null) {
            instance = new StorageFacade();
        }
        return instance;
    }

    @Override
    public ArrayList<Category> getCategories() {
        return null;
    }

    @Override
    public ArrayList<SearchItem> getSearchItemByCategory(long categoryId) {
        return null;
    }

    @Override
    public void createCategory(Category category) {

    }

    @Override
    public void removeCategory(long categoryId) {

    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void createSearchItem(SearchItem searchItem) {

    }

    @Override
    public void removeSearchItem(long searchItemId) {

    }

    @Override
    public void updateSearchItem(SearchItem searchItem) {

    }
}
