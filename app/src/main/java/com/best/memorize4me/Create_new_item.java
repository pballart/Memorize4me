package com.best.memorize4me;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.best.memorize4me.db.StorageFacade;
import com.best.memorize4me.db.model.Category;
import com.best.memorize4me.db.model.Contact;
import com.best.memorize4me.db.model.SearchItem;
import com.best.memorize4me.util.DateUtils;

import java.util.Calendar;


public class Create_new_item extends ActionBarActivity {
    private Category currentCategory;
    private SearchItem currentSearchItem;
    private EditText title;
    private EditText description;
    private EditText contact;
    private EditText tel;
    private EditText mail;
    private EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);
        TextView categoryTitle = (TextView) findViewById(R.id.categoryTitle);
        TextView categoryDate = (TextView) findViewById(R.id.categoryDate);
        currentCategory = (Category) getIntent().getSerializableExtra("category");
        currentSearchItem = (SearchItem) getIntent().getSerializableExtra("search_item");
        title = (EditText) findViewById(R.id.editTitle);
        description = (EditText) findViewById(R.id.editDescription);
        contact = (EditText) findViewById(R.id.editContact);
        tel = (EditText) findViewById(R.id.editTel);
        mail = (EditText) findViewById(R.id.editMail);
        price = (EditText) findViewById(R.id.editPrice);
        categoryTitle.setText(currentCategory.title);
        categoryDate.setText(DateUtils.dateToString(currentCategory.date));
        if (currentSearchItem != null && savedInstanceState == null) {
            title.setText(currentSearchItem.title);
            description.setText(currentSearchItem.description);
            contact.setText(currentSearchItem.contact.name);
            tel.setText(currentSearchItem.contact.email);
            mail.setText(currentSearchItem.contact.email);
            price.setText(String.valueOf(currentSearchItem.price));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_new_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.saveCategory) {
            SearchItem searchItem = new SearchItem();
            Contact newContact = new Contact();
            newContact.email=mail.getText().toString();
            newContact.phoneNumber = tel.getText().toString();
            newContact.name=contact.getText().toString();
            searchItem.categoryId = currentCategory.id;
            searchItem.title=title.getText().toString();
            searchItem.description=description.getText().toString();
            searchItem.contact=newContact;
            searchItem.date = System.currentTimeMillis();
            RatingBar mBar = (RatingBar) findViewById(R.id.ratingBar);
            searchItem.rate = mBar.getRating();
            if (price.getText().toString().matches("")){
                searchItem.price=0;
            }
            else {
                searchItem.price= Float.parseFloat(price.getText().toString());
            }
            if (currentSearchItem != null) {
                searchItem.id = currentSearchItem.id;
                StorageFacade.getInstance().updateSearchItem(searchItem);
            }
            else {
                StorageFacade.getInstance().createSearchItem(searchItem, currentCategory);
            }
            Intent i = new Intent (this, ItemList.class);
            i.putExtra("category", currentCategory);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
