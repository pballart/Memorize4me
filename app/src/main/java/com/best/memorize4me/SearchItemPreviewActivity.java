package com.best.memorize4me;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.best.memorize4me.db.model.SearchItem;

/**
 * Created by Acer on 8.7.2015.
 */
public class SearchItemPreviewActivity extends ActionBarActivity {

    SearchItem searchItem;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item_preview);
        searchItem = (SearchItem) getIntent().getSerializableExtra("search_item");

        TextView title = (TextView) findViewById(R.id.titleTextView);
        TextView date = (TextView) findViewById(R.id.dateTextView);
        TextView price = (TextView) findViewById(R.id.priceTextView);
        TextView rate = (TextView) findViewById(R.id.rateTextView);
        //TextView multilineDescription = (TextView) findViewById(R.id.multilineDescriptionTextView);
        TextView contactName = (TextView) findViewById(R.id.contactNameTextView);
        TextView contactPhoneNumber = (TextView) findViewById(R.id.contactPhoneNumberTextView);
        TextView contactEmail = (TextView) findViewById(R.id.contactEmailTextView);

        title.setText(searchItem.title);
        date.setText(String.valueOf(searchItem.date));
        price.setText(String.valueOf(searchItem.price));
        rate.setText("Rate: " + String.valueOf(searchItem.rate));
        //multilineDescription.setText();
        contactName.setText("Contact: " + searchItem.contact.name);
        contactPhoneNumber.setText("Tel: " + searchItem.contact.phoneNumber);
        contactEmail.setText(searchItem.contact.email);
    }

}
