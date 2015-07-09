package com.best.memorize4me;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
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
    private ItemAdapter adapter;
    ListView listView;
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final SearchItem currentSearchItem = adapter.getItem(info.position);
        if (item.getItemId() == R.id.edit_category) {
            Intent myIntent = new Intent(ItemList.this, Create_new_item.class);
            myIntent.putExtra("category", currentCategory);
            myIntent.putExtra("search_item", currentSearchItem);
            startActivity(myIntent);

        } else if (item.getItemId() == R.id.remove_category) {

            AlertDialog.Builder adb = new AlertDialog.Builder(ItemList.this);
            adb.setTitle("Delete");
            adb.setMessage("Are you sure you want to delete " + currentSearchItem.title + "?");
            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    adapter.remove(currentSearchItem);
                    adapter.notifyDataSetChanged();
                    StorageFacade.getInstance().removeSearchItem(currentSearchItem.id);
                }
            });
            adb.show();
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerForContextMenu(listView);
    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterForContextMenu(listView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        Intent intent = getIntent();
        currentCategory = (Category) intent.getSerializableExtra("category");

        TextView categoryTV = (TextView)findViewById(R.id.textViewCategory);
        categoryTV.setText(currentCategory.title);

        TextView dateTV = (TextView)findViewById(R.id.textViewDate);
        dateTV.setText(DateUtils.dateToString(currentCategory.date));

        listView = (ListView) findViewById(R.id.itemListView);
        ArrayList<SearchItem> searchItems = StorageFacade.getInstance().getSearchItemByCategory(currentCategory.id);

        if (searchItems != null) {
            adapter = new ItemAdapter(this, searchItems);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                SearchItem searchItem = (SearchItem) listView.getItemAtPosition(position);
                Intent myIntent = new Intent(ItemList.this, SearchItemPreviewActivity.class);
                myIntent.putExtra("search_item", searchItem);
                startActivity(myIntent);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
