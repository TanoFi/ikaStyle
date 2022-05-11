package com.example.ikastyle.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.ikastyle.Common.Const.ResourceIdMap;
import com.example.ikastyle.NewFragment;
import com.example.ikastyle.R;

public class GearImageButton extends AppCompatImageButton {
    private int gearId;
    //アタマなら1,フクなら2,クツなら3
    private int gearKind;

    public GearImageButton(Context context, AttributeSet attrs){
        super(context, attrs);

        //未設定時のgearIdを設定
        gearId = 0;

        // カスタム属性gearKindの値を取得しフィールド変数gearKindに入れる
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GearImageButton, 0, 0);
        gearKind = typedArray.getInt(R.styleable.GearImageButton_gearKind, gearKind);

        //this.setOnClickListener(this::onClick);

    }

    public int getGearId(){
        return gearId;
    }

    public void setGearId(int gearId){
        this.gearId = gearId;

        switch(gearKind){
            case 1:
                this.setImageResource(ResourceIdMap.headGearResourceIdMap.get(gearId));
                break;
            case 2:
                this.setImageResource(ResourceIdMap.clothingGearResourceIdMap.get(gearId));
                break;
            case 3:
                this.setImageResource(ResourceIdMap.shoesGearResourceIdMap.get(gearId));
                break;
        }
    }

    public int getGearKind(){
        return gearKind;
    }

//    @Override
//    public abstract void onClick(View view);
}
