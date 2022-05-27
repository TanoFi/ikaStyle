package com.splatool.ikastyle.view.customizedView

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.splatool.ikastyle.R
import android.view.View.OnTouchListener
import android.util.AttributeSet
import android.view.*

/*
 * ギアパワー画像用のImageView
 */
class GearPowerImageView(context: Context, attrs: AttributeSet?) :
    AppCompatImageView(context, attrs), OnTouchListener {
    var gearPowerKind: Int = 0

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        if (motionEvent?.action == MotionEvent.ACTION_DOWN) {
            view?.startDragAndDrop(null, DragShadowBuilder(view), view, 0)
        }
        return true
    }

    init {

        //カスタム属性gearPowerKindの値を取得しフィールド変数gearPowerKindに入れる
        val typedArray =
            getContext().obtainStyledAttributes(attrs, R.styleable.GearPowerImageView, 0, 0)
        gearPowerKind = typedArray.getInt(R.styleable.GearPowerImageView_gearPowerKind, gearPowerKind)

        // onTouchリスナーをセット
        setOnTouchListener(this)
    }
}