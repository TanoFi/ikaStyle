package com.splatool.ikastyle.view.apapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.splatool.ikastyle.R
import com.splatool.ikastyle.model.common.Util
import com.splatool.ikastyle.model.common.const.NumberPlace
import com.splatool.ikastyle.databinding.SpinnerListDropdownItemBinding
import com.splatool.ikastyle.databinding.SpinnerListItemBinding

class KeyValueArrayAdapter(
    context: Context,
    layoutResourceId: Int,
    keyValues: ArrayList<Pair<Int, String>>
) : ArrayAdapter<Pair<Int, String>>(context, layoutResourceId, keyValues) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding= DataBindingUtil.inflate(inflater, R.layout.spinner_list_item, parent, false) as SpinnerListItemBinding

        // ブキIdとブキ名を取得
        val absoluteWeaponId: Int = getItem(position)!!.first
        val weaponName = getItem(position)!!.second

        if (absoluteWeaponId % NumberPlace.MAIN_PLACE == 0) { // メイン単位の項目が選択されている場合
            // 表示する画像がないのでimageViewを消す
            binding.imageViewWeapon.visibility = View.GONE
        } else { // カスタマイズ単位の項目が選択されている場合
            // imageViewに当該のブキ画像セット
            binding.imageViewWeapon.setImageResource(Util.getWeaponResourceId(absoluteWeaponId))
        }
        // textViewにブキ名セット
        binding.textViewWeaponName.text = weaponName
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = DataBindingUtil.inflate(inflater, R.layout.spinner_list_dropdown_item, parent, false) as SpinnerListDropdownItemBinding

        val absoluteWeaponId: Int = getItem(position)!!.first
        val weaponName = getItem(position)!!.second

        // spinner_list_dropdown_item.xmlのレイアウトをインスタンス化する
        if (absoluteWeaponId % NumberPlace.MAIN_PLACE == 0) {
            binding.imageViewWeapon.visibility = View.GONE
        } else {
            binding.imageViewWeapon.setImageResource(Util.getWeaponResourceId(absoluteWeaponId))
        }
        binding.textViewWeaponName.text = weaponName
        return binding.root
    }

    fun resetKeyValues(keyValues: ArrayList<Pair<Int, String>>){
        val deepCopiedList = keyValues.toList()

        clear()
        addAll(deepCopiedList)
        notifyDataSetChanged()
    }

}