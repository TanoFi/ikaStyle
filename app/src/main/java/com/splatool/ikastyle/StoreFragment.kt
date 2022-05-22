package com.splatool.ikastyle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import com.splatool.ikastyle.Common.Util;
import com.splatool.ikastyle.Dao.LoadoutDao;
import com.splatool.ikastyle.Dao.MainCategoryDao;
import com.splatool.ikastyle.Dao.CustomizationNameDao;
import com.splatool.ikastyle.Database.AppDatabase;
import com.splatool.ikastyle.Entity.CustomizationName;
import com.splatool.ikastyle.Entity.Loadout;
import com.splatool.ikastyle.Entity.MainCategory;

import com.splatool.ikastyle.UI.CategorySpinnerSelectedListener;
import com.splatool.ikastyle.UI.KeyValueArrayAdapter;
import com.splatool.ikastyle.UI.CustomizationSpinnerSelectedListener;
import com.splatool.ikastyle.UI.LoadoutDeleteButton;
import com.splatool.ikastyle.UI.LoadoutRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {
    private Spinner categorySpinner;
    private Spinner customizationSpinner;
    private RecyclerView recyclerView;
    private ConstraintLayout emptyView;

    private final int colorNum = Util.getRandomColor();
    private View.OnClickListener onClickDeleteListener;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // deleteボタンを押したときの処理
        onClickDeleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 確認ダイアログを表示
                new AlertDialog.Builder(view.getContext())
                        .setTitle(getString(R.string.dialogMessage_confirm))
                        .setPositiveButton( // Yesを選んだ時
                                getString(R.string.buttonNavigation_yes),
                                new DialogInterface.OnClickListener() {
                                    // データ削除
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        AppDatabase db = AppDatabase.getDatabase(getContext());
                                        Loadout loadout = ((LoadoutDeleteButton)view).getLoadout();
                                        DeleteLoadoutAsyncTask task = new DeleteLoadoutAsyncTask(db, loadout);
                                        task.execute();
                                    }
                                }
                        )
                        .setNegativeButton( // Noを選んだ時
                                getString(R.string.buttonNavigation_no),
                                // 何もしない
                                null
                        )
                        .show();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // スピナーを取得
        categorySpinner = view.findViewById(R.id.spinner_category);
        customizationSpinner = view.findViewById(R.id.spinner_weapon);

        // RecyclerViewを取得
        recyclerView = view.findViewById(R.id.recyclerView_loadouts);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // ギアセット未登録時に表示するViewを設定
        emptyView = view.findViewById(R.id.constrainLayout_emptyInfo);
        ImageView inkMarkImageView = view.findViewById(R.id.imageView_ink_mark);
        inkMarkImageView.setColorFilter(colorNum, PorterDuff.Mode.SRC_ATOP);

        // Spinnerの項目に設定するためのDBを取得
        AppDatabase db = AppDatabase.getDatabase(getContext());
        // SpinnerDataGetAsyncTaskクラス内でContextを取得できなかったためにonPostExecute()だけインスタンス作成時に記述
        GetDataAndSetSpinnerAsyncTask task = new GetDataAndSetSpinnerAsyncTask(db, getContext());
        task.execute();
    }



    /*
     * 非同期でDBからデータ取得しスピナーにセットするクラス
     */
    private class GetDataAndSetSpinnerAsyncTask extends AsyncTask<Void, Void, Integer> {
        private final AppDatabase db;
        private final Context context;

        List<MainCategory> categoryList;
        List<CustomizationName> customizationNameList;

        public GetDataAndSetSpinnerAsyncTask(AppDatabase db, Context context) {
            this.db = db;
            this.context = context;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //端末の言語設定を取得
            int languageCode = Util.getLanguageCode();

            //実際にDBにアクセスし結果を取得
            MainCategoryDao categoryDao = db.mainCategoryDao();
            CustomizationNameDao customizationNameDao = db.customizationNameDao();
            categoryList = categoryDao.getMainCategoryList(languageCode); //ブキカテゴリー名を取得
            customizationNameList = customizationNameDao.getWeaponNameList(languageCode);

            return 0;
        }

        @Override
        protected void onPostExecute(Integer code){
            // Spinnerに渡す用のリストを作成
            ArrayList<Pair<Integer, String>> categoryKeyValueList = new ArrayList<>();
            ArrayList<Pair<Integer, String>> customizationValueList = new ArrayList<>();

            // それぞれのリストの一番上に未選択時の項目を追加
            categoryKeyValueList.add(new Pair<>(0, context.getString(R.string.spinnerItem_categoryUnselected)));
            customizationValueList.add(new Pair<>(0, context.getString(R.string.spinnerItem_weaponUnselected)));

            //それぞれのリストにデータ(IDと名前のペア)を入れる
            categoryList.forEach(x -> categoryKeyValueList.add(new Pair<>(x.getAbsoluteId(), x.getName())));
            customizationNameList.forEach(x -> customizationValueList.add(new Pair<>(x.getAbsoluteId(), x.getName())));

            //アダプター作成
            KeyValueArrayAdapter categoryAdapter = new KeyValueArrayAdapter(context, R.layout.spinner_list_item, categoryKeyValueList);
            KeyValueArrayAdapter customizationAdapter = new KeyValueArrayAdapter(context, R.layout.spinner_list_item, customizationValueList);

            //レイアウトを付与
            categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item);
            customizationAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item);

            //スピナーにアダプターを設定
            categorySpinner.setAdapter(categoryAdapter);
            customizationSpinner.setAdapter(customizationAdapter);

            //リスナーを作成
            CategorySpinnerSelectedListener categoryListener = new CategorySpinnerSelectedListener(context , customizationSpinner, customizationValueList);
            CustomizationSpinnerSelectedListener customizationListener = new CustomizationSpinnerSelectedListener(recyclerView, emptyView,onClickDeleteListener);

            //リスナーを設定
            categorySpinner.setOnItemSelectedListener(categoryListener);
            customizationSpinner.setOnItemSelectedListener(customizationListener);
        }
    }

    /*
     * 非同期でTRAN_GEAR_SETのレコードを削除する
     */
    private class DeleteLoadoutAsyncTask extends AsyncTask<Void, Void, Integer> {
        private final AppDatabase db;
        private final Loadout loadout;

        private  List<Loadout> newLoadout;

        public DeleteLoadoutAsyncTask(AppDatabase db, Loadout loadout) {
            this.db = db;
            this.loadout = loadout;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected Integer doInBackground(Void... params) {
            //実際にDBにアクセスしレコードを削除
            LoadoutDao loadoutDao = db.loadoutDao();
            loadoutDao.DeleteLoadout(loadout);

            // 削除後のギアセットリストを取得し直す
            int selectedWeaponId = ((Pair<Integer, String>)customizationSpinner.getSelectedItem()).first;
            newLoadout = loadoutDao.getLoadoutList(Util.getCategoryId(selectedWeaponId), Util.getMainId(selectedWeaponId), Util.getCustomizationId(selectedWeaponId));

            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            // 選択ギアセット削除後のギアセットリストをrecycleViewにセットし直す
            LoadoutRecyclerViewAdapter newAdapter = new LoadoutRecyclerViewAdapter(newLoadout, onClickDeleteListener);
            recyclerView.setAdapter(newAdapter);

            setEmptyViewVisibility(newLoadout.size());
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