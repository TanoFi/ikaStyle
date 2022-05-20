package jp.java_conf.ikastyle.UI;

import android.content.Context;
import android.util.AttributeSet;
import jp.java_conf.ikastyle.Entity.Loadout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoadoutDeleteButton extends FloatingActionButton {
    private Loadout loadout;

    public LoadoutDeleteButton(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void setLoadout(Loadout loadout) {
        this.loadout = loadout;
    }

    public Loadout getLoadout() {
        return loadout;
    }
}