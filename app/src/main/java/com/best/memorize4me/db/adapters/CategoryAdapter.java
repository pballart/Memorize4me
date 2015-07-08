package com.best.memorize4me.db.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.best.memorize4me.R;
import com.best.memorize4me.db.fakeItUntilYouGetIt.FakeDB;
import com.best.memorize4me.db.model.Category;
import com.best.memorize4me.util.DateUtils;

import java.util.ArrayList;

/**
 * Created by Acer on 7.7.2015.
 */
public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(Context context, ArrayList<Category> category) {
        super(context, 0, category);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Category category = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_list_adapter, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.categoryTitleText);
        TextView date = (TextView) convertView.findViewById(R.id.categoryDateText);
        // Populate the data into the template view using the data object
        title.setText(category.title);
        date.setText(DateUtils.dateToString(category.date));
        // Return the completed view to render on screen
        return convertView;
    }

}
