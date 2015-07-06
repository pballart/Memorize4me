package com.best.memorize4me.db.fakeItUntilYouGetIt;

import com.best.memorize4me.db.model.Category;

import java.util.ArrayList;

/**
 * Created by Acer on 6.7.2015.
 */
public class FakeDB {
    public ArrayList<Category> getCategories() {
        ArrayList<Category> arrayList = new ArrayList<Category>();
        Category cat1 = new Category();
        cat1.id = 1;
        cat1.title = "Hello";
        cat1.date = 12345;
        arrayList.add(cat1);
        Category cat2 = new Category();
        cat2.id = 2;
        cat2.title = "Bye";
        cat2.date = 23456;
        arrayList.add(cat2);
        Category cat3 = new Category();
        cat3.id = 3;
        cat3.title = "Montenigga";
        cat3.date = 452345;
        arrayList.add(cat3);
        return arrayList;
    }
}