package com.best.memorize4me;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private RatingBar mBar;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setDisplayHomeAsUpEnabled(true);
       // actionBar.setIcon(R.mipmap.ic_launcher);

        TextView categoryTitle = (TextView) findViewById(R.id.categoryTitle);
        TextView categoryDate = (TextView) findViewById(R.id.categoryDate);
        currentCategory = (Category) getIntent().getSerializableExtra("category");
        currentSearchItem = getIntent().getParcelableExtra("search_item");
        title = (EditText) findViewById(R.id.editTitle);
        description = (EditText) findViewById(R.id.editDescription);
        contact = (EditText) findViewById(R.id.editContact);
        tel = (EditText) findViewById(R.id.editTel);
        mail = (EditText) findViewById(R.id.editMail);
        price = (EditText) findViewById(R.id.editPrice);
        mBar = (RatingBar) findViewById(R.id.ratingBar);
        imageButton = (ImageButton) findViewById(R.id.imageView2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(intent, "Select File"), 0);
            }
        });

        if (currentCategory != null && savedInstanceState == null) {
            categoryTitle.setText(currentCategory.title);
            categoryDate.setText(DateUtils.dateToString(currentCategory.date));
        }
        if (currentSearchItem != null && savedInstanceState == null) {
            title.setText(currentSearchItem.title);
            description.setText(currentSearchItem.description);
            contact.setText(currentSearchItem.contact.name);
            tel.setText(currentSearchItem.contact.email);
            mail.setText(currentSearchItem.contact.email);
            price.setText(String.valueOf(currentSearchItem.price));
            mBar.setRating(currentSearchItem.rate);
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


            if (title.getText().toString().length() == 0 || description.getText().toString().length() == 0 || contact.getText().toString().length() == 0 || tel.getText().toString().length() == 0 || mail.getText().toString().length() == 0  || price.getText().toString().length() == 0  ) {
                this.showToastWithText("Error: one of the text fields is empty");
            }
            else {
                SearchItem searchItem = new SearchItem();
                Contact newContact = new Contact();
                newContact.email = mail.getText().toString();
                newContact.phoneNumber = tel.getText().toString();
                newContact.name = contact.getText().toString();
                searchItem.categoryId = currentCategory.id;
                searchItem.title = title.getText().toString();
                searchItem.description = description.getText().toString();
                searchItem.contact = newContact;
                searchItem.date = System.currentTimeMillis();
                searchItem.rate = mBar.getRating();
                if (price.getText().toString().matches("")) {
                    searchItem.price = 0;
                } else {
                    searchItem.price = Float.parseFloat(price.getText().toString());
                }
                if (currentSearchItem != null) {
                    searchItem.id = currentSearchItem.id;
                    StorageFacade.getInstance().updateSearchItem(searchItem);
                } else {
                    StorageFacade.getInstance().createSearchItem(searchItem, currentCategory);
                }
                Intent i = new Intent(this, ItemList.class);
                i.putExtra("category", currentCategory);
                startActivity(i);
                finish();
            }
        }
        if (id==android.R.id.home){

            Intent myIntent = new Intent(Create_new_item.this, ItemList.class);
            myIntent.putExtra("category",currentCategory);
            startActivity(myIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void showToastWithText(String text) {
        Context context = getApplicationContext();
        CharSequence textToast = text;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, textToast, duration);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            String[] projection = { MediaStore.MediaColumns.DATA };
            Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                    null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            String selectedImagePath = cursor.getString(column_index);
            Bitmap bm;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(selectedImagePath, options);
            final int REQUIRED_SIZE = 100;
            int scale = 1;
            while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                    && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeFile(selectedImagePath, options);
            imageButton.setImageBitmap(bm);
        }

    }

}
