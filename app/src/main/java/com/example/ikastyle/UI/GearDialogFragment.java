package com.example.ikastyle.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Dao.ClothingGearDao;
import com.example.ikastyle.Dao.GearSetDao;
import com.example.ikastyle.Dao.HeadGearDao;
import com.example.ikastyle.Dao.ShoesGearDao;
import com.example.ikastyle.Database.AppDatabase;
import com.example.ikastyle.Entity.GearSet;
import com.example.ikastyle.R;

import java.util.List;

public class GearDialogFragment extends DialogFragment {
    private RecyclerView recyclerView;

    private int gearKind;

    public GearDialogFragment(int gearKind){
        this.gearKind = gearKind;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.fragment_gear_dialog, null);
        recyclerView = content.findViewById(R.id.recyclerView_gear);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(content.getContext());
        recyclerView.setLayoutManager(layoutManager);

        AppDatabase db = AppDatabase.getDatabase(getContext());
        GetGearListAsyncTask task = new GetGearListAsyncTask(db, gearKind);
        task.execute();

        builder.setView(content);

        return builder.create();
    }

    /*
     * ギアリストをDBから非同期で取得
     */
    private class GetGearListAsyncTask extends AsyncTask<Void, Void, Integer> {
        private AppDatabase db;
        private int gearKind;

        private List<?> gearList;

        private int languageCode;

        public GetGearListAsyncTask(AppDatabase db, int gearKind) {
            this.db = db;
            this.gearKind = gearKind;
            this.languageCode = Util.getLanguageCode();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //実際にDBにアクセスし結果を取得
            switch(gearKind){
                case 1:
                    HeadGearDao headGearDao = db.headGearDao();
                    gearList = headGearDao.getHeadGearList(languageCode);
                    break;
                case 2:
                    ClothingGearDao clothingGearDao = db.clothingGearDao();
                    gearList = clothingGearDao.getClothingGearList(languageCode);
                    break;
                case 3:
                    ShoesGearDao shoesGearDao = db.shoesGearDao();
                    gearList = shoesGearDao.getShoesGearList(languageCode);
                    break;
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer code){
            GearRecyclerViewAdapter adapter = new GearRecyclerViewAdapter(gearList);
            recyclerView.setAdapter(adapter);
        }
    }
}