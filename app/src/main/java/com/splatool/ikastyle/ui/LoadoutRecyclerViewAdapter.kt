package com.splatool.ikastyle.ui

import com.splatool.ikastyle.R
import androidx.recyclerview.widget.RecyclerView
import com.splatool.ikastyle.model.data.entity.Loadout
import android.view.*
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.databinding.CardviewLayoutStoreBinding
import com.splatool.ikastyle.viewModel.StoreViewModel

class LoadoutRecyclerViewAdapter(private val storeViewModel: StoreViewModel) : RecyclerView.Adapter<LoadoutRecyclerViewAdapter.ViewHolder?>() {
    private var loadoutList : List<Loadout> = listOf()

    fun setLoadoutList(loadoutList : List<Loadout>){
        this.loadoutList = loadoutList

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.cardview_layout_store,
            parent,
            false
        ) as CardviewLayoutStoreBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loadout = loadoutList[position]
        holder.binding.loadout = loadout
        holder.binding.viewModel = storeViewModel

        // 画像の設定はDataBindingで出来なさそうなのでここで個別に設定する
        holder.binding.imageViewHeadgear.setImageResource(Util.getHeadGearResourceId(loadout.headGearId))
        holder.binding.imageViewHeadMain.setImageResource(Util.getGearPowerResourceId(loadout.headMain))
        holder.binding.imageViewHeadSub1.setImageResource(Util.getGearPowerResourceId(loadout.headSub1))
        holder.binding.imageViewHeadSub2.setImageResource(Util.getGearPowerResourceId(loadout.headSub2))
        holder.binding.imageViewHeadSub3.setImageResource(Util.getGearPowerResourceId(loadout.headSub3))
        holder.binding.imageViewClothing.setImageResource(Util.getClothingResourceId(loadout.clothingGearId))
        holder.binding.imageViewClothingMain.setImageResource(Util.getGearPowerResourceId(loadout.clothingMain))
        holder.binding.imageViewClothingSub1.setImageResource(Util.getGearPowerResourceId(loadout.clothingSub1))
        holder.binding.imageViewClothingSub2.setImageResource(Util.getGearPowerResourceId(loadout.clothingSub2))
        holder.binding.imageViewClothingSub3.setImageResource(Util.getGearPowerResourceId(loadout.clothingSub3))
        holder.binding.imageViewShoes.setImageResource(Util.getShoesResourceId(loadout.shoesGearId))
        holder.binding.imageViewShoesMain.setImageResource(Util.getGearPowerResourceId(loadout.shoesMain))
        holder.binding.imageViewShoesSub1.setImageResource(Util.getGearPowerResourceId(loadout.shoesSub1))
        holder.binding.imageViewShoesSub2.setImageResource(Util.getGearPowerResourceId(loadout.shoesSub2))
        holder.binding.imageViewShoesSub3.setImageResource(Util.getGearPowerResourceId(loadout.shoesSub3))

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return loadoutList.size
    }

    class ViewHolder(val binding : CardviewLayoutStoreBinding) : RecyclerView.ViewHolder(binding.root)
}