package com.splatool.ikastyle.ui

import androidx.recyclerview.widget.RecyclerView
import com.splatool.ikastyle.model.data.database.AppDatabase
import android.os.AsyncTask
import com.splatool.ikastyle.model.data.entity.Loadout
import android.widget.Spinner
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.*
import com.splatool.ikastyle.common.Util

class CustomizationSpinnerSelectedListener(
    private val recyclerView: RecyclerView,
    private val emptyView: ConstraintLayout,
    private val onClickDeleteListener: View.OnClickListener
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}