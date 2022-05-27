package com.splatool.ikastyle.ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.splatool.ikastyle.R
import com.splatool.ikastyle.common.const.GearPowerPositionKind

class GearPowerReceptorImageView// カスタム属性receptorKindの値を取得しフィールド変数gearPowerKindに入れる
    (context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    var receptorKind: GearPowerPositionKind

    init {
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