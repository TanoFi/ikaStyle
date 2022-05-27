package com.splatool.ikastyle.ui

import com.splatool.ikastyle.common.const.GearKind
import com.splatool.ikastyle.R
import androidx.recyclerview.widget.RecyclerView
import com.splatool.ikastyle.model.data.entity.HeadGear
import com.splatool.ikastyle.model.data.entity.ClothingGear
import com.splatool.ikastyle.model.data.entity.ShoesGear
import android.view.*
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.databinding.CardviewLayoutStoreBinding
import com.splatool.ikastyle.databinding.FragmentGearDialogBinding
import com.splatool.ikastyle.databinding.GearListItemBinding
import com.splatool.ikastyle.viewModel.GearDialogViewModel

class GearRecyclerViewAdapter(private val gearDialogViewModel: GearDialogViewModel) :
    RecyclerView.Adapter<GearRecyclerViewAdapter.ViewHolder?>() {
    private var gearList : List<*> = listOf<HeadGear>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.gear_list_item,
            parent,
            false
        ) as GearListItemBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = gearDialogViewModel
        when (val gear = gearList[position]) {
            is HeadGear -> {
                holder.binding.imageViewGear.setImageResource(Util.getHeadGearResourceId(gear.id))
                holder.binding.textViewGearName.text = gear.name
                holder.binding.linearLayoutGearListItem.gearId = gear.id
            }
            is ClothingGear -> {
                holder.binding.imageViewGear.setImageResource(Util.getClothingResourceId(gear.id))
                holder.binding.textViewGearName.text = gear.name
                holder.binding.linearLayoutGearListItem.gearId = gear.id
            }
            is ShoesGear -> {
                holder.binding.imageViewGear.setImageResource(Util.getShoesResourceId(gear.id))
                holder.binding.textViewGearName.text = gear.name
                holder.binding.linearLayoutGearListItem.gearId = gear.id
            }
        }

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return gearList.size
    }

    fun setGearList(gearList : List<*>){
        this.gearList = gearList

        notifyDataSetChanged()
    }


    class ViewHolder(val binding: GearListItemBinding) : RecyclerView.ViewHolder(binding.root)
}