package com.example.ikastyle.UI;


import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.R;

import java.util.ArrayList;

public class KeyValueArrayAdapter extends ArrayAdapter<Pair<Integer, String>> {
    private final LayoutInflater inflater;

    private static class ViewHolder{
        ImageView weaponImageView;
        TextView weaponNameView;
    }

    public KeyValueArrayAdapter(Context context, int layoutResourceId, ArrayList<Pair<Integer,String>> keyValues){
        super(context, layoutResourceId, keyValues);

        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // ブキIdとブキ名を取得
        int absoluteWeaponId = getItem(position).first;
        String weaponName = getItem(position).second;

        // spinner_list_item.xmlのレイアウトをインスタンス化する
        View view = inflater.inflate(R.layout.spinner_list_item, null);
        ViewHolder holder = new ViewHolder();
        holder.weaponNameView = view.findViewById(R.id.textView_weaponName);
        holder.weaponImageView = view.findViewById(R.id.imageView_weapon);

        if(absoluteWeaponId % NumberPlace.MAIN_PLACE == 0){ // メイン単位の項目が選択されている場合
            // 表示する画像がないのでimageViewを消す
            holder.weaponImageView.setVisibility(View.GONE);
        }
        else { // カスタマイズ単位の項目が選択されている場合
            // imageViewに当該のブキ画像セット
            holder.weaponImageView.setImageResource(Util.getWeaponResourceId(absoluteWeaponId));
        }
        // textViewにブキ名セット
        holder.weaponNameView.setText(weaponName);

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent){
        int absoluteWeaponId = getItem(position).first;
        String weaponName = getItem(position).second;

        // spinner_list_dropdown_item.xmlのレイアウトをインスタンス化する
        View view = inflater.inflate(R.layout.spinner_list_dropdown_item, null);
        ViewHolder holder = new ViewHolder();
        holder.weaponNameView = view.findViewById(R.id.textView_weaponName);
        holder.weaponImageView = view.findViewById(R.id.imageView_weapon);
        if(absoluteWeaponId % NumberPlace.MAIN_PLACE == 0){
            holder.weaponImageView.setVisibility(View.GONE);
        }
        else {
            holder.weaponImageView.setImageResource(Util.getWeaponResourceId(absoluteWeaponId));
        }
        holder.weaponNameView.setText(weaponName);

        return view;
    }
}
