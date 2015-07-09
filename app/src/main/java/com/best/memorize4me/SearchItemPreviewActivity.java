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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.memorize4me.db.model.SearchItem;
import com.best.memorize4me.util.DateUtils;

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
        TextView multilineDescription = (TextView) findViewById(R.id.multilineDescriptionTextView);
        TextView contactName = (TextView) findViewById(R.id.contactNameTextView);
        final TextView contactPhoneNumber = (TextView) findViewById(R.id.contactPhoneNumberTextView);
        TextView contactEmail = (TextView) findViewById(R.id.contactEmailTextView);
        imageView = (ImageView) findViewById(R.id.imageView);

        title.setText(searchItem.title);
        date.setText(DateUtils.dateToString(searchItem.date));
        multilineDescription.setText (searchItem.description);
        price.setText(String.valueOf(searchItem.price)+ " €");
        rate.setText("Rate: " + String.valueOf(searchItem.rate));
        contactName.setText("Full Name: " + searchItem.contact.name);
        contactPhoneNumber.setText("Tel: " + searchItem.contact.phoneNumber);
        contactEmail.setText(searchItem.contact.email);
        if (searchItem.imageUrl != null) {
            setImage(searchItem.imageUrl);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(new File(searchItem.imageUrl)), "image/png");
                    startActivity(intent);
                }
            });
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        ImageView callImageView = (ImageView) findViewById(R.id.callImageView);
        callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(contactPhoneNumber.getText().toString()));
                startActivity(callIntent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setImage(String path) {
        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        final int REQUIRED_SIZE = 100;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);
        imageView.setImageBitmap(bm);
    }
}
