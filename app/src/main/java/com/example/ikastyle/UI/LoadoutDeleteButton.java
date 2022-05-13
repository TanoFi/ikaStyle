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

public class LoadoutDeleteButton extends FloatingActionButton implements View.OnClickListener {
    private GearSet gearSet;

    public LoadoutDeleteButton(Context context, AttributeSet attrs){
        super(context, attrs);

        this.setOnClickListener(this::onClick);
    }

    public void setGearSet(GearSet gearSet) {
        this.gearSet = gearSet;
    }

    @Override
    public void onClick(View view) {
        AppDatabase db = AppDatabase.getDatabase(getContext());
        DeleteGearSetAsyncTask task = new DeleteGearSetAsyncTask(db, gearSet);
        task.execute();
    }

    /*
     * 非同期でTRAN_GEAR_SETのレコードを削除する
     */
    private class DeleteGearSetAsyncTask extends AsyncTask<Void, Void, Integer> {
        private AppDatabase db;
        private GearSet gearSet;

        public DeleteGearSetAsyncTask(AppDatabase db, GearSet gearSet) {
            this.db = db;
            this.gearSet = gearSet;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //実際にDBにアクセスしレコードを削除
            GearSetDao gearSetDao = db.gearSetDao();
            gearSetDao.DeleteGearSet(gearSet);

            return 0;
        }
    }
}
