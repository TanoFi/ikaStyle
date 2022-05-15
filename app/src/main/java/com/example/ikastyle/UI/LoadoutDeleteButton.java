package com.example.ikastyle.UI;

import android.content.Context;
import android.util.AttributeSet;
import com.example.ikastyle.Entity.GearSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoadoutDeleteButton extends FloatingActionButton {
    private GearSet gearSet;

    public LoadoutDeleteButton(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void setGearSet(GearSet gearSet) {
        this.gearSet = gearSet;
    }

    public GearSet getGearSet() {
        return gearSet;
    }
}
