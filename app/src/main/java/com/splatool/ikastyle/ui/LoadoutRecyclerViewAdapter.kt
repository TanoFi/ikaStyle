package com.splatool.ikastyle.ui

import com.splatool.ikastyle.R
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.splatool.ikastyle.entity.Loadout
import android.view.*
import android.widget.*
import com.splatool.ikastyle.common.Util

class LoadoutRecyclerViewAdapter(
    private val loadoutList: List<Loadout>,
    private val onClickDeleteListener: View.OnClickListener?
) : RecyclerView.Adapter<LoadoutRecyclerViewAdapter.ViewHolder?>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val loadoutNameView: TextView = view.findViewById(R.id.textView_loadoutName)
        val headGearView: ImageView = view.findViewById(R.id.imageView_headgear)
        val headMainView: ImageView = view.findViewById(R.id.imageView_head_main)
        val headSub1View: ImageView = view.findViewById(R.id.imageView_head_sub1)
        val headSub2View: ImageView = view.findViewById(R.id.imageView_head_sub2)
        val headSub3View: ImageView = view.findViewById(R.id.imageView_head_sub3)
        val clothingGearView: ImageView = view.findViewById(R.id.imageView_clothing)
        val clothingMainView: ImageView = view.findViewById(R.id.imageView_clothing_main)
        val clothingSub1View: ImageView = view.findViewById(R.id.imageView_clothing_sub1)
        val clothingSub2View: ImageView = view.findViewById(R.id.imageView_clothing_sub2)
        val clothingSub3View: ImageView = view.findViewById(R.id.imageView_clothing_sub3)
        val shoesGearView: ImageView = view.findViewById(R.id.imageView_shoes)
        val shoesMainView: ImageView = view.findViewById(R.id.imageView_shoes_main)
        val shoesSub1View: ImageView = view.findViewById(R.id.imageView_shoes_sub1)
        val shoesSub2View: ImageView = view.findViewById(R.id.imageView_shoes_sub2)
        val shoesSub3View: ImageView = view.findViewById(R.id.imageView_shoes_sub3)
        //val analyseView: FloatingActionButton = view.findViewById(R.id.floatingActionButton_analyse)
        //val editView: FloatingActionButton = view.findViewById(R.id.floatingActionButton_edit)
        val deleteView: LoadoutDeleteButton = view.findViewById(R.id.floatingActionButton_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_layout_store, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loadout = loadoutList[position]
        holder.loadoutNameView.text = loadout.name
        holder.headGearView.setImageResource(Util.getHeadGearResourceId(loadout.headGearId))
        holder.headMainView.setImageResource(Util.getGearPowerResourceId(loadout.headMain))
        holder.headSub1View.setImageResource(Util.getGearPowerResourceId(loadout.headSub1))
        holder.headSub2View.setImageResource(Util.getGearPowerResourceId(loadout.headSub2))
        holder.headSub3View.setImageResource(Util.getGearPowerResourceId(loadout.headSub3))
        holder.clothingGearView.setImageResource(Util.getClothingResourceId(loadout.clothingGearId))
        holder.clothingMainView.setImageResource(Util.getGearPowerResourceId(loadout.clothingMain))
        holder.clothingSub1View.setImageResource(Util.getGearPowerResourceId(loadout.clothingSub1))
        holder.clothingSub2View.setImageResource(Util.getGearPowerResourceId(loadout.clothingSub2))
        holder.clothingSub3View.setImageResource(Util.getGearPowerResourceId(loadout.clothingSub3))
        holder.shoesGearView.setImageResource(Util.getShoesResourceId(loadout.shoesGearId))
        holder.shoesMainView.setImageResource(Util.getGearPowerResourceId(loadout.shoesMain))
        holder.shoesSub1View.setImageResource(Util.getGearPowerResourceId(loadout.shoesSub1))
        holder.shoesSub2View.setImageResource(Util.getGearPowerResourceId(loadout.shoesSub2))
        holder.shoesSub3View.setImageResource(Util.getGearPowerResourceId(loadout.shoesSub3))
        holder.deleteView.loadout = loadout
        holder.deleteView.setOnClickListener(onClickDeleteListener)
    }

    override fun getItemCount(): Int {
        return loadoutList.size
    }
}