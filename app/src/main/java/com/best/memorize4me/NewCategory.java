package com.best.memorize4me;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.best.memorize4me.db.fakeItUntilYouGetIt.FakeDB;
import com.best.memorize4me.db.interfaces.AppInterface;
import com.best.memorize4me.db.model.Category;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class NewCategory extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_category, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.saveCategory){
            View v = findViewById(R.id.saveCategory);
            Category newCategory=new Category();
            DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
            Calendar c = Calendar.getInstance();
            c.set (dp.getYear(),dp.getMonth(),dp.getDayOfMonth());
            long startTime = c.getTimeInMillis();
            EditText title = (EditText) findViewById(R.id.editText);
            newCategory.title=title.getText().toString();
            newCategory.date= startTime;

            //todo save newCategory to database
            Intent i = new Intent (this, CategoryList.class);
            startActivity (i);
        }
        return super.onOptionsItemSelected(item);
    }

}
