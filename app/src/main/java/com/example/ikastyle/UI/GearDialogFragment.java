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

import com.example.ikastyle.Common.Const.GearKind;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Dao.ClothingGearDao;
import com.example.ikastyle.Dao.GearSetDao;
import com.example.ikastyle.Dao.HeadGearDao;
import com.example.ikastyle.Dao.ShoesGearDao;
import com.example.ikastyle.Database.AppDatabase;
import com.example.ikastyle.Entity.GearSet;
import com.example.ikastyle.NewFragment;
import com.example.ikastyle.R;

import java.util.List;

/*
 * ギア一覧を表示するダイアログ
 */
public class GearDialogFragment extends DialogFragment {
    private RecyclerView recyclerView;
    private GearKind gearKind;

    public GearDialogFragment(GearKind gearKind){
        this.gearKind = gearKind;
    }

    // このinterfaceを呼び出し元フラグメントで実装することで呼び出し元フラグメントの更新を行う
    public interface GearDialogListener{
        void onListItemClick(GearDialogFragment dialogFragment, GearKind gearKind, int gearId);
    }

    private GearDialogListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try {
            // 親フラグメントに実装されているインターフェースを取得
            listener = (GearDialogListener) getParentFragment();
        } catch (ClassCastException e) {
            // 親フラグメントがインターフェースを実装していない場合は例外を投げる
            throw new ClassCastException(getParentFragment().toString() + "はインターフェースを実装していません");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // fragment_gear_dialog.xmlのレイアウトをViewとしてインスタンス化
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.fragment_gear_dialog, null);

        // fragment_gear_dialog.xml上のRecyclerViewを取得しレイアウト設定
        recyclerView = content.findViewById(R.id.recyclerView_gear);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(content.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // DBからギアデータのリストを取得しRecyclerViewに表示
        AppDatabase db = AppDatabase.getDatabase(getContext());
        GetGearListAsyncTask task = new GetGearListAsyncTask(db, gearKind);
        task.execute();

        // ダイアログにキャンセルボタン設置
        builder.setNegativeButton(getString(R.string.buttonNavigation_cancel), null);

        // ダイアログにViewを設定
        builder.setView(content);

        return builder.create();
    }

    /*
     * ギアリストをDBから非同期で取得
     */
    private class GetGearListAsyncTask extends AsyncTask<Void, Void, Integer> {
        private AppDatabase db;
        private GearKind gearKind;

        private List<?> gearList;

        private int languageCode;

        public GetGearListAsyncTask(AppDatabase db, GearKind gearKind) {
            this.db = db;
            this.gearKind = gearKind;
            this.languageCode = Util.getLanguageCode();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //実際にDBにアクセスし結果を取得
            switch(gearKind){
                case HEAD:
                    HeadGearDao headGearDao = db.headGearDao();
                    gearList = headGearDao.getHeadGearList(languageCode);
                    break;
                case CLOTHING:
                    ClothingGearDao clothingGearDao = db.clothingGearDao();
                    gearList = clothingGearDao.getClothingGearList(languageCode);
                    break;
                case SHOES:
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
            // ギアリストのItemを選択した時の処理
            adapter.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int gearId = ((GearListItemLinearLayout)view).getGearId();
                    GearKind gearKind = ((GearListItemLinearLayout)view).getGearKind();
                    // 元Fragment(NewFragment)にコールバック
                    listener.onListItemClick(GearDialogFragment.this, gearKind, gearId);
                    // ダイアログを消す
                    dismiss();
                }
            });
        }
    }
}