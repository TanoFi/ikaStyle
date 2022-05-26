package com.splatool.ikastyle.ui

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.splatool.ikastyle.R
import android.view.View.OnDragListener
import com.splatool.ikastyle.common.const.GearPowerPositionKind
import android.util.AttributeSet
import android.view.*
import com.splatool.ikastyle.common.Util

class GearPowerReceptorImageView : AppCompatImageView, OnDragListener {
    var gearPowerKind = 0
    set(value) {
        field = value

        setImageResource(Util.getGearPowerResourceId(value))
    }
    var receptorKind: GearPowerPositionKind? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

        // カスタム属性receptorKindの値を取得しフィールド変数gearPowerKindに入れる
        val typedArray =
            getContext().obtainStyledAttributes(attrs, R.styleable.GearPowerReceptorImageView, 0, 0)
        receptorKind = GearPowerPositionKind.getGearPowerPositionKind(
            typedArray.getInt(
                R.styleable.GearPowerReceptorImageView_receptorKind,
                0
            )
        )

        // onDragリスナーをセット
        setOnDragListener(this)
    }


    override fun onDrag(view: View?, dragEvent: DragEvent?): Boolean {
        if (dragEvent?.action == DragEvent.ACTION_DROP) {
            val dragView = dragEvent.localState as GearPowerImageView
            val gearPowerKind = dragView.gearPowerKind
            // 一部のギアパワーは特定のギアのメインギアにしか付けられないためこのようなif条件を入れている(e.g.ラストスパートはアタマのメインギアにしか付けられない)
            if (gearPowerKind / 100 == 0 || gearPowerKind / 100 == receptorKind?.getId()) {
                // フィールド変数gearPowerKindをセットしながら、セットしたKindのギア画像に差し替え
                (view as GearPowerReceptorImageView).gearPowerKind = gearPowerKind
            }
        }
        return true
    }

    // 初期化用メソッド
    fun init() {
        gearPowerKind = 0
    }
}