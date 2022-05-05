package com.example.ikastyle.UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.ikastyle.R;

/*
 * ギアパワー画像用のImageView
 */
public class GearPowerImageView extends AppCompatImageView {
    private int gearPowerKind;

    public GearPowerImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        initialize(attrs);
    }

    /*
     * カスタム属性gearPowerKindの値を取得しフィールド変数gearPowerKindに入れる
     */
    private void initialize(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GearPowerImageView, 0, 0);
        gearPowerKind = typedArray.getInt(R.styleable.GearPowerImageView_gearPowerKind, gearPowerKind);
    }
}