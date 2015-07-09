package com.best.memorize4me;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.best.memorize4me.db.StorageFacade;
import com.best.memorize4me.db.adapters.CategoryAdapter;
import com.best.memorize4me.db.fakeItUntilYouGetIt.FakeDB;
import com.best.memorize4me.db.model.Category;

import java.util.ArrayList;


public class CategoryList extends ActionBarActivity {

    ArrayList<Category> arrayOfCategories;
    CategoryAdapter adapter;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();

        final Category currentCategory = adapter.getItem(info.position);
        if (item.getItemId()==R.id.edit_category) {
            Intent myIntent = new Intent(CategoryList.this, NewCategory.class);
            myIntent.putExtra("category", currentCategory);
            startActivity(myIntent);
            finish();

        } else if (item.getItemId()==R.id.remove_category) {

            AlertDialog.Builder adb = new AlertDialog.Builder(CategoryList.this);
            adb.setTitle("Delete");
            adb.setMessage("Are you sure you want to delete " + currentCategory.title + "?");
            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                adapter.remove(currentCategory);
                adapter.notifyDataSetChanged();
                arrayOfCategories.remove(currentCategory);
                StorageFacade.getInstance().removeCategory(currentCategory.id);
                }
            });
            adb.show();
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        actionBar.setIcon(R.mipmap.ic_launcher);

        final ListView listView = (ListView) findViewById(R.id.categoryListView);
        arrayOfCategories = StorageFacade.getInstance().getCategories();
        if (arrayOfCategories != null) {
            adapter = new CategoryAdapter(this, arrayOfCategories);
            listView.setAdapter(adapter);
            registerForContextMenu(listView);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Object o = listView.getItemAtPosition(position);
                Category cat = (Category) o;
                Intent myIntent = new Intent(CategoryList.this, ItemList.class);
                myIntent.putExtra("category", cat);
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
            startActivity(myIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
