package com.splatool.ikastyle.view.customizedView

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.splatool.ikastyle.R
import com.splatool.ikastyle.model.data.entity.Loadout

class LoadoutDeleteButton(context: Context, attrs: AttributeSet?) :
    FloatingActionButton(context, attrs) {
    var loadout: Loadout? = null
    var loadoutId : Int = 0

    init{
        // カスタム属性gearKindの値を取得しフィールド変数gearKindに入れる
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoadoutDeleteButton, 0, 0)
        loadoutId = typedArray.getInt(R.styleable.LoadoutDeleteButton_loadoutId, 0)
    }
}