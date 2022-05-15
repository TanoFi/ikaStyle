package com.example.ikastyle.UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.ikastyle.Common.Const.GearKind;
import com.example.ikastyle.Common.Util;
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
                this.setImageResource(Util.getHeadGearResourceId(gearId));
                break;
            case CLOTHING:
                this.setImageResource(Util.getClothingResourceId(gearId));
                break;
            case SHOES:
                this.setImageResource(Util.getShoesResourceId(gearId));
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
                this.setImageResource(Util.getHeadGearResourceId(gearId));
                break;
            case CLOTHING:
                this.setImageResource(Util.getClothingResourceId(gearId));
                break;
            case SHOES:
                this.setImageResource(Util.getShoesResourceId(gearId));
                break;
        }
    }
}
