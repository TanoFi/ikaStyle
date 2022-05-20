package jp.java_conf.ikastyle.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import jp.java_conf.ikastyle.Common.Const.GearKind;

/*
 * GearIdとGearKindを管理できるLinearLayoutクラス
 * ギアリストを表示するダイアログ上で使用
 */
public class GearListItemLinearLayout extends LinearLayout {
    private int gearId = 0;
    private GearKind gearKind;

    public GearListItemLinearLayout(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public int getGearId() {
        return gearId;
    }

    public void setGearId(int gearId) {
        this.gearId = gearId;
    }

    public GearKind getGearKind(){
        return gearKind;
    }

    public void setGearKind(GearKind gearKind) {
        this.gearKind = gearKind;
    }
}
