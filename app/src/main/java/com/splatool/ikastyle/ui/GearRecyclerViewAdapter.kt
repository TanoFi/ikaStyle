package com.splatool.ikastyle.ui

import com.splatool.ikastyle.common.const.GearKind
import com.splatool.ikastyle.R
import androidx.recyclerview.widget.RecyclerView
import com.splatool.ikastyle.model.data.entity.HeadGear
import com.splatool.ikastyle.model.data.entity.ClothingGear
import com.splatool.ikastyle.model.data.entity.ShoesGear
import android.view.*
import android.widget.*
import com.splatool.ikastyle.common.Util

class GearRecyclerViewAdapter(private val gearList: List<*>) :
    RecyclerView.Adapter<GearRecyclerViewAdapter.ViewHolder>() {
    private var listener: View.OnClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val linearLayout: GearListItemLinearLayout = view.findViewById(R.id.linearLayout_gearListItem)
        val gearView: ImageView = view.findViewById(R.id.imageView_gear)
        val gearNameView: TextView = view.findViewById(R.id.textView_gearName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gear_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val gear = gearList[position]) {
            is HeadGear -> {
                holder.gearView.setImageResource(Util.getHeadGearResourceId(gear.id))
                holder.gearNameView.text = gear.name
                holder.linearLayout.gearId = gear.id
                holder.linearLayout.gearKind = GearKind.HEAD
            }
            is ClothingGear -> {
                holder.gearView.setImageResource(Util.getClothingResourceId(gear.id))
                holder.gearNameView.text = gear.name
                holder.linearLayout.gearId = gear.id
                holder.linearLayout.gearKind = GearKind.CLOTHING
            }
            is ShoesGear -> {
                holder.gearView.setImageResource(Util.getShoesResourceId(gear.id))
                holder.gearNameView.text = gear.name
                holder.linearLayout.gearId = gear.id
                holder.linearLayout.gearKind = GearKind.SHOES
            }
        }
        holder.linearLayout.setOnClickListener(View.OnClickListener { view: View? ->
            listener?.onClick(
                view
            )
        })
    }

    fun setOnItemClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return gearList.size
    }
}