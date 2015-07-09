package com.best.memorize4me;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.memorize4me.db.model.SearchItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Acer on 8.7.2015.
 */
public class SearchItemPreviewActivity extends ActionBarActivity {

    private static final int GET_IMAGE_REQUEST_CODE = 1;
    SearchItem searchItem;
    ImageView imageView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item_preview);
        getSupportActionBar().setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        searchItem = (SearchItem) getIntent().getParcelableExtra("search_item");

        TextView title = (TextView) findViewById(R.id.titleTextView);
        TextView date = (TextView) findViewById(R.id.dateTextView);
        TextView price = (TextView) findViewById(R.id.priceTextView);
        TextView rate = (TextView) findViewById(R.id.rateTextView);
        //TextView multilineDescription = (TextView) findViewById(R.id.multilineDescriptionTextView);
        TextView contactName = (TextView) findViewById(R.id.contactNameTextView);
        TextView contactPhoneNumber = (TextView) findViewById(R.id.contactPhoneNumberTextView);
        TextView contactEmail = (TextView) findViewById(R.id.contactEmailTextView);
        imageView = (ImageView) findViewById(R.id.imageView);

        title.setText(searchItem.title);
        date.setText(String.valueOf(searchItem.date));
        price.setText(String.valueOf(searchItem.price));
        rate.setText("Rate: " + String.valueOf(searchItem.rate));
        contactName.setText("Contact: " + searchItem.contact.name);
        contactPhoneNumber.setText("Tel: " + searchItem.contact.phoneNumber);
        contactEmail.setText(searchItem.contact.email);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, GET_IMAGE_REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GET_IMAGE_REQUEST_CODE)
        {
            if(data != null)
            {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                photo = Bitmap.createScaledBitmap(photo, 80, 80, false);
                imageView.setImageBitmap(photo);
            }
        }
    }
}
