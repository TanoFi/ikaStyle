package com.splatool.ikastyle.ui

import android.content.Context
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.splatool.ikastyle.entity.Loadout
import android.util.AttributeSet

class LoadoutDeleteButton(context: Context, attrs: AttributeSet?) :
    FloatingActionButton(context, attrs) {
    var loadout: Loadout? = null
}