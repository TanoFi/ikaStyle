package com.example.ikastyle.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikastyle.Common.Const.ResourceIdMap;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Entity.GearSet;
import com.example.ikastyle.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LoadoutRecyclerViewAdapter extends RecyclerView.Adapter<LoadoutRecyclerViewAdapter.ViewHolder> {
    private final List<GearSet> gearSetList;
    private final View.OnClickListener onClickDeleteListener;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView loadoutNameView;
        private ImageView headGearView;
        private ImageView headMainView;
        private ImageView headSub1View;
        private ImageView headSub2View;
        private ImageView headSub3View;
        private ImageView clothingGearView;
        private ImageView clothingMainView;
        private ImageView clothingSub1View;
        private ImageView clothingSub2View;
        private ImageView clothingSub3View;
        private ImageView shoesGearView;
        private ImageView shoesMainView;
        private ImageView shoesSub1View;
        private ImageView shoesSub2View;
        private ImageView shoesSub3View;
        private FloatingActionButton analyseView;
        private FloatingActionButton editView;
        private LoadoutDeleteButton deleteView;

        public ViewHolder(View view){
            super(view);
            loadoutNameView = view.findViewById(R.id.textView_loadoutName);
            headGearView = view.findViewById(R.id.imageView_headgear);
            headMainView = view.findViewById(R.id.imageView_head_main);
            headSub1View = view.findViewById(R.id.imageView_head_sub1);
            headSub2View = view.findViewById(R.id.imageView_head_sub2);
            headSub3View = view.findViewById(R.id.imageView_head_sub3);
            clothingGearView = view.findViewById(R.id.imageView_clothing);
            clothingMainView = view.findViewById(R.id.imageView_clothing_main);
            clothingSub1View = view.findViewById(R.id.imageView_clothing_sub1);
            clothingSub2View = view.findViewById(R.id.imageView_clothing_sub2);
            clothingSub3View = view.findViewById(R.id.imageView_clothing_sub3);
            shoesGearView = view.findViewById(R.id.imageView_shoes);
            shoesMainView = view.findViewById(R.id.imageView_shoes_main);
            shoesSub1View = view.findViewById(R.id.imageView_shoes_sub1);
            shoesSub2View = view.findViewById(R.id.imageView_shoes_sub2);
            shoesSub3View = view.findViewById(R.id.imageView_shoes_sub3);
            analyseView = view.findViewById(R.id.floatingActionButton_analyse);
            editView = view.findViewById(R.id.floatingActionButton_edit);
            deleteView = view.findViewById(R.id.floatingActionButton_delete);
        }
    }

    public LoadoutRecyclerViewAdapter(List<GearSet> gearSetList, View.OnClickListener onClickDeleteListener){
        this.gearSetList = gearSetList;
        this.onClickDeleteListener = onClickDeleteListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout_store, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        GearSet gearSet = gearSetList.get(position);

        holder.loadoutNameView.setText(gearSet.name);
        holder.headGearView.setImageResource(Util.getHeadGearResourceId(gearSet.headGearId));
        holder.headMainView.setImageResource(Util.getGearPowerResourceId(gearSet.headMain));
        holder.headSub1View.setImageResource(Util.getGearPowerResourceId(gearSet.headSub1));
        holder.headSub2View.setImageResource(Util.getGearPowerResourceId(gearSet.headSub2));
        holder.headSub3View.setImageResource(Util.getGearPowerResourceId(gearSet.headSub3));
        holder.clothingGearView.setImageResource(Util.getClothingResourceId(gearSet.clothingGearId));
        holder.clothingMainView.setImageResource(Util.getGearPowerResourceId(gearSet.clothingMain));
        holder.clothingSub1View.setImageResource(Util.getGearPowerResourceId(gearSet.clothingSub1));
        holder.clothingSub2View.setImageResource(Util.getGearPowerResourceId(gearSet.clothingSub2));
        holder.clothingSub3View.setImageResource(Util.getGearPowerResourceId(gearSet.clothingSub3));
        holder.shoesGearView.setImageResource(Util.gerShoesResourceId(gearSet.shoesGearId));
        holder.shoesMainView.setImageResource(Util.getGearPowerResourceId(gearSet.shoesMain));
        holder.shoesSub1View.setImageResource(Util.getGearPowerResourceId(gearSet.shoesSub1));
        holder.shoesSub2View.setImageResource(Util.getGearPowerResourceId(gearSet.shoesSub2));
        holder.shoesSub3View.setImageResource(Util.getGearPowerResourceId(gearSet.shoesSub3));
        holder.deleteView.setGearSet(gearSet);
        holder.deleteView.setOnClickListener(onClickDeleteListener);
    }

    @Override
    public int getItemCount(){
        return gearSetList.size();
    }
}
