package com.example.ikastyle.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.ikastyle.Common.Const.GearKind;
import com.example.ikastyle.Common.Const.ResourceIdMap;
import com.example.ikastyle.NewFragment;
import com.example.ikastyle.R;

public class GearImageView extends AppCompatImageView{
    private int gearId  = 0;
    private final GearKind gearKind;

    public GearImageView(Context context, AttributeSet attrs){
        super(context, attrs);

        // カスタム属性gearKindの値を取得しフィールド変数gearKindに入れる
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GearImageView, 0, 0);
        gearKind = GearKind.getGearKind(typedArray.getInt(R.styleable.GearImageView_gearKind, 0));
    }

    public int getGearId(){
        return gearId;
    }

    public void setGearId(int gearId){
        this.gearId = gearId;

        // gearIdのブキの画像をセット
        switch(gearKind){
            case HEAD:
                this.setImageResource(ResourceIdMap.headGearResourceIdMap.get(gearId));
                break;
            case CLOTHING:
                this.setImageResource(ResourceIdMap.clothingGearResourceIdMap.get(gearId));
                break;
            case SHOES:
                this.setImageResource(ResourceIdMap.shoesGearResourceIdMap.get(gearId));
                break;
        }
    }

    public GearKind getGearKind(){
        return gearKind;
    }

    // 初期化用メソッド
    public void init(){
        // ギアIDを0に設定
        this.gearId = 0;

        // ギア画像を初期アイコンに設定
        switch(gearKind){
            case HEAD:
                this.setImageResource(ResourceIdMap.headGearResourceIdMap.get(gearId));
                break;
            case CLOTHING:
                this.setImageResource(ResourceIdMap.clothingGearResourceIdMap.get(gearId));
                break;
            case SHOES:
                this.setImageResource(ResourceIdMap.shoesGearResourceIdMap.get(gearId));
                break;
        }
    }
}
