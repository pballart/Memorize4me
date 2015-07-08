package com.best.memorize4me;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.best.memorize4me.db.adapters.CategoryAdapter;
import com.best.memorize4me.db.fakeItUntilYouGetIt.FakeDB;
import com.best.memorize4me.db.model.Category;

import java.util.ArrayList;


public class CategoryList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        // Construct the data source
        ArrayList<Category> arrayOfCategories = new ArrayList<Category>();
        arrayOfCategories = FakeDB.getCategories();
// Create the adapter to convert the array to views
        CategoryAdapter adapter = new CategoryAdapter(this, arrayOfCategories);
// Attach the adapter to a ListView
         final ListView listView = (ListView) findViewById(R.id.categoryListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Object o = listView.getItemAtPosition(position);
                Category cat = (Category)o;
                Intent myIntent = new Intent(CategoryList.this, ItemList.class);
                myIntent.putExtra("category_id",cat.id);
                CategoryList.this.startActivity(myIntent);
                Log.d("caca", String.valueOf(cat.id));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_list, menu);
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
            Log.d("add category pressed", "hostia");
            Intent myIntent = new Intent(CategoryList.this, NewCategory.class);
            CategoryList.this.startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
