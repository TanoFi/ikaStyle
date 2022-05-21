package com.splatool.ikastyle.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.splatool.ikastyle.Common.Util;
import com.splatool.ikastyle.Entity.Loadout;
import com.splatool.ikastyle.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LoadoutRecyclerViewAdapter extends RecyclerView.Adapter<LoadoutRecyclerViewAdapter.ViewHolder> {
    private final List<Loadout> loadoutList;
    private final View.OnClickListener onClickDeleteListener;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView loadoutNameView;
        private final ImageView headGearView;
        private final ImageView headMainView;
        private final ImageView headSub1View;
        private final ImageView headSub2View;
        private final ImageView headSub3View;
        private final ImageView clothingGearView;
        private final ImageView clothingMainView;
        private final ImageView clothingSub1View;
        private final ImageView clothingSub2View;
        private final ImageView clothingSub3View;
        private final ImageView shoesGearView;
        private final ImageView shoesMainView;
        private final ImageView shoesSub1View;
        private final ImageView shoesSub2View;
        private final ImageView shoesSub3View;
        private final FloatingActionButton analyseView;
        private final FloatingActionButton editView;
        private final LoadoutDeleteButton deleteView;

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

    public LoadoutRecyclerViewAdapter(List<Loadout> loadoutList, View.OnClickListener onClickDeleteListener){
        this.loadoutList = loadoutList;
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
        Loadout loadout = loadoutList.get(position);

        holder.loadoutNameView.setText(loadout.name);
        holder.headGearView.setImageResource(Util.getHeadGearResourceId(loadout.headGearId));
        holder.headMainView.setImageResource(Util.getGearPowerResourceId(loadout.headMain));
        holder.headSub1View.setImageResource(Util.getGearPowerResourceId(loadout.headSub1));
        holder.headSub2View.setImageResource(Util.getGearPowerResourceId(loadout.headSub2));
        holder.headSub3View.setImageResource(Util.getGearPowerResourceId(loadout.headSub3));
        holder.clothingGearView.setImageResource(Util.getClothingResourceId(loadout.clothingGearId));
        holder.clothingMainView.setImageResource(Util.getGearPowerResourceId(loadout.clothingMain));
        holder.clothingSub1View.setImageResource(Util.getGearPowerResourceId(loadout.clothingSub1));
        holder.clothingSub2View.setImageResource(Util.getGearPowerResourceId(loadout.clothingSub2));
        holder.clothingSub3View.setImageResource(Util.getGearPowerResourceId(loadout.clothingSub3));
        holder.shoesGearView.setImageResource(Util.getShoesResourceId(loadout.shoesGearId));
        holder.shoesMainView.setImageResource(Util.getGearPowerResourceId(loadout.shoesMain));
        holder.shoesSub1View.setImageResource(Util.getGearPowerResourceId(loadout.shoesSub1));
        holder.shoesSub2View.setImageResource(Util.getGearPowerResourceId(loadout.shoesSub2));
        holder.shoesSub3View.setImageResource(Util.getGearPowerResourceId(loadout.shoesSub3));
        holder.deleteView.setLoadout(loadout);
        holder.deleteView.setOnClickListener(onClickDeleteListener);
    }

    @Override
    public int getItemCount(){
        return loadoutList.size();
    }
}
