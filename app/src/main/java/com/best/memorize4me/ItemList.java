package com.best.memorize4me;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.best.memorize4me.db.StorageFacade;
import com.best.memorize4me.db.adapters.ItemAdapter;
import com.best.memorize4me.db.fakeItUntilYouGetIt.FakeDB;
import com.best.memorize4me.db.model.Category;
import com.best.memorize4me.db.model.SearchItem;
import com.best.memorize4me.util.DateUtils;

import java.util.ArrayList;


public class ItemList extends ActionBarActivity {
    private Category currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        Intent intent = getIntent();
        currentCategory = (Category) intent.getSerializableExtra("category");

        TextView categoryTV = (TextView)findViewById(R.id.textViewCategory);
        categoryTV.setText(currentCategory.title);

        TextView dateTV = (TextView)findViewById(R.id.textViewDate);
        dateTV.setText(DateUtils.dateToString(currentCategory.date));

        final ListView listView = (ListView) findViewById(R.id.itemListView);
        ArrayList<SearchItem> searchItems = StorageFacade.getInstance().getSearchItemByCategory(currentCategory.id);

        if (searchItems != null) {
            ItemAdapter adapter = new ItemAdapter(this, searchItems);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Object o = listView.getItemAtPosition(position);
               /*
                Category cat = (Category) o;
                Intent myIntent = new Intent(CategoryList.this, ItemList.class);
                myIntent.putExtra("category_id", cat.id);
                CategoryList.this.startActivity(myIntent);
                */
                Log.d("caca", "caca");
            }
        });
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
            Intent intent = new Intent(this, Create_new_item.class);
            intent.putExtra("category", currentCategory);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(ItemList.this, CategoryList.class);
        startActivity(myIntent);
    }
}
