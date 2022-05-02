package com.example.ikastyle.UI;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.R;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class WeaponSpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

    private Context context;
    private Spinner spinner;
    private ArrayList<Pair<Integer,String>> keyValueList;

    public WeaponSpinnerSelectedListener(Context context, Spinner spinner, ArrayList<Pair<Integer,String>> keyValueList){
        this.context = context;
        this.spinner = spinner;
        this.keyValueList = keyValueList;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner)adapterView;
        // 絶対IDからカテゴリーIDを割り出す
        int categoryId = ((Pair<Integer, String>)spinner.getSelectedItem()).first / NumberPlace.CATEGORY_PLACE;

        ArrayList<Pair<Integer, String>> selectedKeyValueList;

        if(categoryId == 0){ // カテゴリーSpinnerで未選択項目が選ばれているとき
            //ブキSpinnerの項目を全表示にする
            selectedKeyValueList = keyValueList;
        }
        else {
            // カテゴリーSpinnerで選択したカテゴリーに属するブキだけをブキSpinnerに表示される
            selectedKeyValueList = (ArrayList<Pair<Integer, String>>) keyValueList.stream().
                    filter(x -> x.first / NumberPlace.CATEGORY_PLACE == categoryId || x.first == 0).
                    collect(Collectors.toList());
        }

        KeyValueArrayAdapter newAdapter = new KeyValueArrayAdapter(context, android.R.layout.simple_spinner_item, selectedKeyValueList);
        newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner.setAdapter(newAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
