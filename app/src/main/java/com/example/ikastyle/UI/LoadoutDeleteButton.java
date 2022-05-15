package com.example.ikastyle.UI;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Dao.GearSetDao;
import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Dao.WeaponMainDao;
import com.example.ikastyle.Database.AppDatabase;
import com.example.ikastyle.DatabaseView.WeaponMain;
import com.example.ikastyle.Entity.GearSet;
import com.example.ikastyle.Entity.MainCategory;
import com.example.ikastyle.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
