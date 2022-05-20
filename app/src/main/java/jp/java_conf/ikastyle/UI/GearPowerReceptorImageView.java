package jp.java_conf.ikastyle.UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import jp.java_conf.ikastyle.Common.Const.GearKind;
import jp.java_conf.ikastyle.Common.Util;
import jp.java_conf.ikastyle.R;

public class GearPowerReceptorImageView extends AppCompatImageView implements View.OnDragListener {
    private int gearPowerKind = 0;
    private GearKind receptorKind;

    public GearPowerReceptorImageView(Context context){
        super(context);
    }

    public GearPowerReceptorImageView(Context context, AttributeSet attrs){
        super(context, attrs);

        // カスタム属性receptorKindの値を取得しフィールド変数gearPowerKindに入れる
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GearPowerReceptorImageView, 0, 0);
        receptorKind = GearKind.getGearKind(typedArray.getInt(R.styleable.GearPowerReceptorImageView_receptorKind, 0));

        // onDragリスナーをセット
        this.setOnDragListener(this);
    }

    public int getGearPowerKind(){
        return gearPowerKind;
    }

    public void setGearPowerKind(int gearPowerKind){
        this.gearPowerKind = gearPowerKind;

        // セットされたGearPowerKindの画像に差し替え
        this.setImageResource(Util.getGearPowerResourceId(gearPowerKind));
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent){
        if(dragEvent.getAction() == DragEvent.ACTION_DROP){
            GearPowerImageView dragView = (GearPowerImageView) dragEvent.getLocalState();

            int gearPowerKind = dragView.getGearPowerKind();
            // 一部のギアパワーは特定のギアのメインギアにしか付けられないためこのようなif条件を入れている(e.g.ラストスパートはアタマのメインギアにしか付けられない)
            if(gearPowerKind / 100 == 0 || gearPowerKind / 100 == receptorKind.getId()){
                // フィールド変数gearPowerKindをセットしながら、セットしたKindのギア画像に差し替え
                ((GearPowerReceptorImageView)view).setGearPowerKind(gearPowerKind);
            }
        }
        return true;
    }

    // 初期化用メソッド
    public void init(){
        // ギアパワーKindを0に設定
        gearPowerKind = 0;

        // ギアパワー画像を未設定時のものに設定
        this.setImageResource(Util.getGearPowerResourceId(gearPowerKind));
    }
}
