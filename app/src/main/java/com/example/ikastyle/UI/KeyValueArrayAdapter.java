package com.example.ikastyle.UI;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KeyValueArrayAdapter extends ArrayAdapter<Pair<Integer, String>> {

    public KeyValueArrayAdapter(Context context, int resourceId){
        super(context, resourceId);
    }

    public KeyValueArrayAdapter(Context context, int resourceId, ArrayList<Pair<Integer,String>> keyValues){
        super(context, resourceId, keyValues);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setText(getItem(position).second);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setText(getItem(position).second);
        return view;
    }
}
