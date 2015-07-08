package com.best.memorize4me.db.interfaces;

import android.app.Activity;
import android.database.Cursor;

import com.best.memorize4me.db.model.Category;
import com.best.memorize4me.db.model.SearchItem;

import java.util.ArrayList;

/**
 * Created by konstantin.bogdanov on 06.07.2015.
 */
public interface AppInterface {
    public ArrayList<Category> getCategories();
    public ArrayList<SearchItem> getSearchItemByCategory(long categoryId);
    public void createCategory(Category category);
    public void removeCategory(long categoryId);
    public void updateCategory(Category category);
    public void createSearchItem(SearchItem searchItem, Category category);
    public void removeSearchItem(long searchItemId);
    public void updateSearchItem(SearchItem searchItem);
}
