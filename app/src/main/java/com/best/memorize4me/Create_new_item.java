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

import com.best.memorize4me.db.model.Category;
import com.best.memorize4me.db.model.Contact;
import com.best.memorize4me.db.model.SearchItem;

import java.util.Calendar;


public class Create_new_item extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);
        TextView categoryTitle = (TextView) findViewById(R.id.categoryTitle);
        TextView categoryDate = (TextView) findViewById(R.id.categoryDate);

        //todo:  get from DB category and obtain date and title
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_new_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.saveCategory) {
            SearchItem searchItem = new SearchItem();
            EditText title = (EditText) findViewById(R.id.editTitle);
            EditText description = (EditText) findViewById(R.id.editDescription);
            EditText contact = (EditText) findViewById(R.id.editContact);
            EditText tel = (EditText) findViewById(R.id.editTel);
            EditText mail = (EditText) findViewById(R.id.editMail);
            EditText longdescription = (EditText) findViewById(R.id.editMultiline);
            EditText price = (EditText) findViewById(R.id.editPrice);

            Contact newContact = new Contact();
            newContact.email=mail.getText().toString();
            newContact.phoneNumber = tel.getText().toString();
            newContact.name=contact.getText().toString();
            //todo: add contact lastName
            searchItem.title=title.getText().toString();
            searchItem.description=description.getText().toString();
            searchItem.contact=newContact;
            searchItem.multilineDescription = longdescription.getText().toString();
            searchItem.date = System.currentTimeMillis();
            RatingBar mBar = (RatingBar) findViewById(R.id.ratingBar);
            searchItem.rate = mBar.getRating();
            if (price.getText().toString().matches("")){
                searchItem.price=0;
            }
            else {
                searchItem.price= Float.parseFloat(price.getText().toString());
            }
            //todo save newItem to database
            Intent i = new Intent (this, ItemList.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);

    }

}
