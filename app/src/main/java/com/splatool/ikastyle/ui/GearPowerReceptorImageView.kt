package com.splatool.ikastyle.ui

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.splatool.ikastyle.R
import android.view.View.OnDragListener
import com.splatool.ikastyle.common.const.GearPowerPositionKind
import android.util.AttributeSet
import android.view.*
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.databinding.FragmentNewBinding

class GearPowerReceptorImageView : AppCompatImageView{

    var receptorKind: GearPowerPositionKind

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

        // カスタム属性receptorKindの値を取得しフィールド変数gearPowerKindに入れる
        val typedArray =
            getContext().obtainStyledAttributes(attrs, R.styleable.GearPowerReceptorImageView, 0, 0)
        receptorKind = GearPowerPositionKind.getGearPowerPositionKind(
            typedArray.getInt(
                R.styleable.GearPowerReceptorImageView_receptorKind,
                0
            )
        )!!
    }
}