package com.splatool.ikastyle.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.splatool.ikastyle.R
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.common.const.NumberPlace

class KeyValueArrayAdapter(
    context: Context,
    layoutResourceId: Int,
    keyValues: ArrayList<Pair<Int, String>>
) : ArrayAdapter<Pair<Int, String>>(context, layoutResourceId, keyValues) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private class ViewHolder(view : View) {
        val weaponImageView: ImageView = view.findViewById(R.id.imageView_weapon)
        val weaponNameView: TextView = view.findViewById(R.id.textView_weaponName)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // ブキIdとブキ名を取得
        val absoluteWeaponId: Int = getItem(position)!!.first
        val weaponName = getItem(position)!!.second

        // spinner_list_item.xmlのレイアウトをインスタンス化する
        val view = inflater.inflate(R.layout.spinner_list_item, null)
        val holder = ViewHolder(view)
        if (absoluteWeaponId % NumberPlace.MAIN_PLACE == 0) { // メイン単位の項目が選択されている場合
            // 表示する画像がないのでimageViewを消す
            holder.weaponImageView.visibility = View.GONE
        } else { // カスタマイズ単位の項目が選択されている場合
            // imageViewに当該のブキ画像セット
            holder.weaponImageView.setImageResource(Util.getWeaponResourceId(absoluteWeaponId))
        }
        // textViewにブキ名セット
        holder.weaponNameView.text = weaponName
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val absoluteWeaponId: Int = getItem(position)!!.first
        val weaponName = getItem(position)!!.second

        // spinner_list_dropdown_item.xmlのレイアウトをインスタンス化する
        val view = inflater.inflate(R.layout.spinner_list_dropdown_item, null)
        val holder = ViewHolder(view)
        if (absoluteWeaponId % NumberPlace.MAIN_PLACE == 0) {
            holder.weaponImageView.visibility = View.GONE
        } else {
            holder.weaponImageView.setImageResource(Util.getWeaponResourceId(absoluteWeaponId))
        }
        holder.weaponNameView.text = weaponName
        return view
    }

}