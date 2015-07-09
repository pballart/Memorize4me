package com.best.memorize4me.db.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.best.memorize4me.R;
import com.best.memorize4me.db.fakeItUntilYouGetIt.FakeDB;
import com.best.memorize4me.db.model.SearchItem;
import com.best.memorize4me.util.DateUtils;

import java.util.ArrayList;

/**
 * Created by Acer on 7.7.2015.
 */
public class ItemAdapter extends ArrayAdapter<SearchItem> {
    public ItemAdapter(Context context, ArrayList<SearchItem> searchItems) {
        super(context, 0, searchItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SearchItem searchItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_adapter, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.itemTitleTextView);
        TextView date = (TextView) convertView.findViewById(R.id.itemDateTextView);
        TextView description = (TextView) convertView.findViewById(R.id.itemDescriptionTextView);
        TextView price = (TextView) convertView.findViewById(R.id.itemPriceTextView);
        TextView rate = (TextView) convertView.findViewById(R.id.itemRateTextView);
        // Populate the data into the template view using the data object
        title.setText(searchItem.title);
        date.setText(DateUtils.dateToString(searchItem.date));
        description.setText(searchItem.description);
        price.setText(String.valueOf(searchItem.price)+" €");
        rate.setText("Rate: "+String.valueOf(searchItem.rate));
        // Return the completed view to render on screen
        return convertView;
    }
}
