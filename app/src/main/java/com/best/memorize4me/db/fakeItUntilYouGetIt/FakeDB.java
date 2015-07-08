package com.best.memorize4me.db.fakeItUntilYouGetIt;

import com.best.memorize4me.db.model.Category;
import com.best.memorize4me.db.model.Contact;
import com.best.memorize4me.db.model.SearchItem;

import java.util.ArrayList;

/**
 * Created by Acer on 6.7.2015.
 */
public class FakeDB {
    static public ArrayList<Category> getCategories() {
        ArrayList<Category> arrayList = new ArrayList<Category>();
        Category cat1 = new Category();
        cat1.id = 1;
        cat1.title = "Hello";
        cat1.date = 1436343416;
        arrayList.add(cat1);
        Category cat2 = new Category();
        cat2.id = 2;
        cat2.title = "Bye";
        cat2.date = 36343413;
        arrayList.add(cat2);
        Category cat3 = new Category();
        cat3.id = 3;
        cat3.title = "Montenigga";
        cat3.date = 1236343222;
        arrayList.add(cat3);
        Category c4 = new Category(3, "isdfho", 1452);
        arrayList.add(c4);
        return arrayList;
    }

    static public ArrayList<SearchItem> getSearchItemByCategory(long categoryId) {
        ArrayList<SearchItem> array = new ArrayList<SearchItem>();
        SearchItem s1 = new SearchItem();
        s1.id = 1;
        s1.title = "Hola";
        s1.date = 1236343222;
        s1.price = 34;
        s1.rate = 4;
        Contact c1 = new Contact();
        c1.firstName = "Pepe";
        c1.lastName = "Botella";
        c1.email = "a@a.com";
        c1.phoneNumber = "123456";
        s1.contact = c1;
        s1.description = "Hola gaaat";
        s1.multilineDescription = "How are you? \n Lorem Ipsum је једноставно модел текста који се користи у штампарској и словослагачкој индустрији. \nLorem ipsum је био стандард за модел текста још од 1500. године, када је непознати штампар узео кутију са словима и сложио их како би направио узорак књиге. Не само што је овај модел опстао пет векова, него је чак почео да \nсе користи и у електронским медијима, непроменивши се. Популаризован је шездесетих година двадесетог века заједно са листовима летерсета који су садржали Lorem Ipsum пасусе, а данас са софтверским пакетом за прелом као што је Aldus PageMaker који је садржао Lorem Ipsum верзије.";

       if (categoryId == 1) {
           array.add(s1);
       }


        SearchItem s2 = new SearchItem();
        s2.id = 1;
        s2.title = "Adei";
        s2.date = 1231343222;
        s2.price = 32224;
        s2.rate = 1;
        Contact c2 = new Contact();
        c2.firstName = "cipoton";
        c2.lastName = "maduro";
        c2.email = "asdfasdf@a.com";
        c2.phoneNumber = "123asdf456";
        s2.contact = c2;
        s2.description = "Gat i Goos";
        s2.multilineDescription = "Fine thanks";
        array.add(s2);
        return  array;
    }
}