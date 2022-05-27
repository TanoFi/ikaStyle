package com.splatool.ikastyle.ui

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.splatool.ikastyle.common.const.GearKind
import com.splatool.ikastyle.R
import android.util.AttributeSet
import com.splatool.ikastyle.common.Util

class GearImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    var gearId = 0
    set(value) {
        field = value

        //ギア画像を設定
        when (gearKind) {
            GearKind.HEAD -> setImageResource(Util.getHeadGearResourceId(gearId))
            GearKind.CLOTHING -> setImageResource(Util.getClothingResourceId(gearId))
            GearKind.SHOES -> setImageResource(Util.getShoesResourceId(gearId))
        }
    }
    val gearKind : GearKind

    init {
        // カスタム属性gearKindの値を取得しフィールド変数gearKindに入れる
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GearImageView, 0, 0)
        gearKind = GearKind.getGearKind(typedArray.getInt(R.styleable.GearImageView_gearKind, 0))!!
    }
}