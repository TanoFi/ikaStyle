package com.example.ikastyle.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikastyle.Common.Const.GearKind;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Entity.ClothingGear;
import com.example.ikastyle.Entity.GearSet;
import com.example.ikastyle.Entity.HeadGear;
import com.example.ikastyle.Entity.ShoesGear;
import com.example.ikastyle.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class GearRecyclerViewAdapter extends RecyclerView.Adapter<GearRecyclerViewAdapter.ViewHolder> {
    private final List<?> gearList;
    private View.OnClickListener listener;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private GearListItemLinearLayout linearLayout;
        private ImageView gearView;
        private TextView gearNameView;

        public ViewHolder(View view){
            super(view);
            linearLayout = view.findViewById(R.id.linearLayout_gearListItem);
            gearView = view.findViewById(R.id.imageView_gear);
            gearNameView = view.findViewById(R.id.textView_gearName);
        }
    }

    public GearRecyclerViewAdapter(List<?> gearList){
        this.gearList = gearList;
    }

    @Override
    @NonNull
    public GearRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gear_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GearRecyclerViewAdapter.ViewHolder holder, int position){
        Object gear = gearList.get(position);

        if(gear instanceof HeadGear){
            HeadGear headGear = (HeadGear)gear;
            holder.gearView.setImageResource(Util.getHeadGearResourceId(headGear.id));
            holder.gearNameView.setText(headGear.name);
            holder.linearLayout.setGearId(headGear.id);
            holder.linearLayout.setGearKind(GearKind.HEAD);
        }
        else if(gear instanceof ClothingGear){
            ClothingGear clothingGear = (ClothingGear)gear;
            holder.gearView.setImageResource(Util.getClothingResourceId(clothingGear.id));
            holder.gearNameView.setText(clothingGear.name);
            holder.linearLayout.setGearId(clothingGear.id);
            holder.linearLayout.setGearKind(GearKind.CLOTHING);

        }
        else if(gear instanceof ShoesGear){
            ShoesGear shoesGear = (ShoesGear)gear;
            holder.gearView.setImageResource(Util.gerShoesResourceId(shoesGear.id));
            holder.gearNameView.setText(shoesGear.name);
            holder.linearLayout.setGearId(shoesGear.id);
            holder.linearLayout.setGearKind(GearKind.SHOES);
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
    }

    public void setOnItemClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount(){
        return gearList.size();
    }
}
