package com.example.ikastyle.UI;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Dao.GearSetDao;
import com.example.ikastyle.Database.AppDatabase;
import com.example.ikastyle.Entity.GearSet;
import com.example.ikastyle.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomizationSpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
    private RecyclerView recyclerView;
    private ConstraintLayout emptyView;
    private View.OnClickListener onClickDeleteListener;

    public CustomizationSpinnerSelectedListener(RecyclerView recyclerView, ConstraintLayout emptyView, View.OnClickListener onClickDeleteListener){
        this.recyclerView = recyclerView;
        this.emptyView = emptyView;
        this.onClickDeleteListener = onClickDeleteListener;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner)adapterView;
        // Spinnerで選択したブキの絶対ID
        int absoluteId = ((Pair<Integer, String>) spinner.getSelectedItem()).first;

        AppDatabase db = AppDatabase.getDatabase(spinner.getContext());
        GetGearSetListAsyncTask task = new GetGearSetListAsyncTask(db, absoluteId);
        task.execute();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*
     * 選択したブキのギアセットリストをDBから非同期で取得
     */
    public class GetGearSetListAsyncTask extends AsyncTask<Void, Void, Integer> {
        private AppDatabase db;
        private int absoluteId;

        private List<GearSet> gearSetList;

        public GetGearSetListAsyncTask(AppDatabase db, int absoluteId) {
            this.db = db;
            this.absoluteId = absoluteId;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //実際にDBにアクセスし結果を取得
            GearSetDao gearSetDao = db.gearSetDao();
            gearSetList = gearSetDao.getGearSetList(Util.getCategoryId(absoluteId), Util.getMainId(absoluteId), Util.getCustomizationId(absoluteId));

            return 0;
        }

        @Override
        protected void onPostExecute(Integer code){
            LoadoutRecyclerViewAdapter adapter = new LoadoutRecyclerViewAdapter(gearSetList, onClickDeleteListener);
            recyclerView.setAdapter(adapter);

            setEmptyViewVisibility(gearSetList.size());
        }

        // 表示するギアセットがあればEmptyViewは非表示、なければ表示
        private void setEmptyViewVisibility(int size){
            if(size == 0){
                emptyView.setVisibility(View.VISIBLE);
            }
            else {
                emptyView.setVisibility(View.GONE);
            }
        }
    }
}
