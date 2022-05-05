package com.example.ikastyle.UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.ikastyle.Common.Const.GearPowerResourceId;
import com.example.ikastyle.R;

/*
 * ギアパワー画像用のImageView
 */
public class GearPowerImageView extends AppCompatImageView implements View.OnTouchListener {
    private int gearPowerKind;

    public GearPowerImageView(Context context, AttributeSet attrs){
        super(context, attrs);

        //カスタム属性gearPowerKindの値を取得しフィールド変数gearPowerKindに入れる
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GearPowerImageView, 0, 0);
        gearPowerKind = typedArray.getInt(R.styleable.GearPowerImageView_gearPowerKind, gearPowerKind);

        // onTouchリスナーをセット
        this.setOnTouchListener(this::onTouch);
    }

    public int getGearPowerKind(){
        return gearPowerKind;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            view.startDragAndDrop(null, new View.DragShadowBuilder(view), view, 0);
        }

        return true;
    }
}