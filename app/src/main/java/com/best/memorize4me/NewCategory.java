package com.best.memorize4me;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.best.memorize4me.db.StorageFacade;
import com.best.memorize4me.db.fakeItUntilYouGetIt.FakeDB;
import com.best.memorize4me.db.interfaces.AppInterface;
import com.best.memorize4me.db.model.Category;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class NewCategory extends ActionBarActivity {
    private EditText titleTxt;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        titleTxt = (EditText) findViewById(R.id.editText);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
    }

    private Category getCategoryFromFields() {
        Category category = new Category();
        category.title = titleTxt.getText().toString();
        Date date = getDateFromDatePicker(datePicker);
        category.date = date.getTime();
        return category;
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
            if (titleTxt.getText().toString().length() == 0) {
                this.showToastWithText("Error: one of the text fields is empty");
            } else {
                View v = findViewById(R.id.saveCategory);
                Category category = getCategoryFromFields();
                StorageFacade.getInstance().createCategory(category);
                this.finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public void showToastWithText(String text) {
        Context context = getApplicationContext();
        CharSequence textToast = text;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, textToast, duration);
        toast.show();
    }
}
