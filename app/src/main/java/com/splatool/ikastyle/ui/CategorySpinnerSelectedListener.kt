package com.splatool.ikastyle.ui

import android.content.Context
import com.splatool.ikastyle.R
import com.splatool.ikastyle.common.const.NumberPlace
import android.widget.Spinner
import android.widget.AdapterView
import android.view.*
import java.util.ArrayList

class CategorySpinnerSelectedListener(
    private val context: Context,
    private val spinner: Spinner,
    private val keyValueList: ArrayList<Pair<Int, String>>
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val spinner = adapterView as Spinner

        // 絶対IDからカテゴリーIDを割り出す
        val categoryId = ((spinner.selectedItem as Pair<*, *>).first as Int) / NumberPlace.CATEGORY_PLACE

        val selectedKeyValueList: ArrayList<Pair<Int, String>> = if (categoryId == 0) { // カテゴリーSpinnerで未選択項目が選ばれているとき
            //ブキSpinnerの項目を全表示にする
            keyValueList
        } else {
            // カテゴリーSpinnerで選択したカテゴリーに属するブキだけをブキSpinnerに表示される
            (keyValueList.filter{it.first / NumberPlace.CATEGORY_PLACE == categoryId || it.first == 0}.toMutableList()) as ArrayList<Pair<Int, String>>
        }
        val newAdapter =
            KeyValueArrayAdapter(context, R.layout.spinner_list_item, selectedKeyValueList)
        newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.spinner.adapter = newAdapter
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}