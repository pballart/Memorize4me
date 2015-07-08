package com.best.memorize4me;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.best.memorize4me.db.fakeItUntilYouGetIt.FakeDB;
import com.best.memorize4me.db.model.Category;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ItemList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ArrayList<Category> arrayOfCategories = new ArrayList<Category>();
        arrayOfCategories = FakeDB.getCategories();

        Intent intent = getIntent();
        long id = intent.getLongExtra("category_id", 1);
        Category cat = arrayOfCategories.get((int) (Long.valueOf(id) - 1));

        TextView categoryTV = (TextView)findViewById(R.id.textViewCategory);
        categoryTV.setText(cat.title);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date(cat.date * 1000));

        TextView dateTV = (TextView)findViewById(R.id.textViewDate);
        dateTV.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_category) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
