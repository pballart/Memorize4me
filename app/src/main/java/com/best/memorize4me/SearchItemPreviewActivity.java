package com.best.memorize4me;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.memorize4me.db.model.SearchItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Acer on 8.7.2015.
 */
public class SearchItemPreviewActivity extends ActionBarActivity {

    SearchItem searchItem;

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
        TextView contactPhoneNumber = (TextView) findViewById(R.id.contactPhoneNumberTextView);
        TextView contactEmail = (TextView) findViewById(R.id.contactEmailTextView);
        ImageView photo = (ImageView) findViewById(R.id.imageView);

        title.setText(searchItem.title);
        date.setText(String.valueOf(searchItem.date));
        price.setText(String.valueOf(searchItem.price) + " €");
        rate.setText("Rate: " + String.valueOf(searchItem.rate));
        contactName.setText("Contact: " + searchItem.contact.name);
        contactPhoneNumber.setText("Tel: " + searchItem.contact.phoneNumber);
        multilineDescription.setText(searchItem.description);
        contactEmail.setText(searchItem.contact.email);

        try {

            URL url = new URL("http://www.getdigital.eu/web/getdigital/gfx/products/__generated__resized/1100x1100/Aufkleber_Trollface.jpg");
            HttpGet httpGet = new HttpGet(url.toURI());
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = (HttpResponse) httpClient.execute(httpGet);

            HttpEntity httpEntity = httpResponse.getEntity();
            BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(httpEntity);
            InputStream inputStream = bufferedHttpEntity.getContent();

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            photo.setImageBitmap(bitmap);

        } catch (Exception ex) {

        }

    }

}
