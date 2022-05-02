package com.example.ikastyle;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Dao.MainNameDao;
import com.example.ikastyle.Dao.WeaponMainDao;
import com.example.ikastyle.Dao.WeaponNameDao;
import com.example.ikastyle.Database.AppDatabase;
import com.example.ikastyle.DatabaseView.WeaponMain;
import com.example.ikastyle.Entity.MainCategory;
import com.example.ikastyle.Entity.MainName;
import com.example.ikastyle.Entity.WeaponName;
import com.example.ikastyle.UI.KeyValueArrayAdapter;
import com.example.ikastyle.UI.WeaponSpinnerSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Spinnerの項目に設定するためのDBを取得
        AppDatabase db = AppDatabase.getDatabase(getContext());
        // SpinnerDataGetAsyncTaskクラス内でContextを取得できなかったためにonPostExecute()だけインスタンス作成時に記述
        GetWeaponNameAsyncTask task = new GetWeaponNameAsyncTask(db){
            @Override
            protected void onPostExecute(Integer code){
                // ひとつのメインウェポン : そのメインウェポンを持つブキセットのリスト でMapを作成
                // 昇順でソートしたいのでTreeMap
                TreeMap<Integer, List<WeaponMain>> weaponMainMap = new TreeMap<>(weaponMainList.stream().collect(
                        Collectors.groupingBy(x -> x.categoryId * NumberPlace.CATEGORY_PLACE + x.mainId * NumberPlace.MAIN_PLACE)
                ));

                // Spinnerに渡す用のリストを作成
                ArrayList<Pair<Integer, String>> categoryKeyValueList = new ArrayList<>();
                ArrayList<Pair<Integer, String>> mainWeaponKeyValueList = new ArrayList<>();

                // それぞれのリストの一番上に未選択時の項目を追加
                categoryKeyValueList.add(new Pair<>(0, getString(R.string.spinnerItem_categoryUnselected)));
                mainWeaponKeyValueList.add(new Pair<>(0, getString(R.string.spinnerItem_weaponUnselected)));

                //それぞれのリストにデータ(IDと名前のペア)を入れる
                categoryList.forEach(x -> categoryKeyValueList.add(new Pair<>(x.getAbsoluteId(), x.getName())));
                for (Map.Entry<Integer, List<WeaponMain>> data :weaponMainMap.entrySet()) {
                    int key = data.getKey();
                    List<WeaponMain> value = data.getValue();
                    mainWeaponKeyValueList.add(new Pair<>(key, value.get(0).getMainName())); //メインウェポンのデータをAdd

                    value.forEach(x -> mainWeaponKeyValueList.add(new Pair<>(x.getAbsoluteId(), x.getWeaponName()))); //ブキセットのデータをAdd
                }

                //スピナー取得
                Spinner categorySpinner = view.findViewById(R.id.spinner_category);
                Spinner weaponSpinner = view.findViewById(R.id.spinner_weapon);

                //アダプター作成
                KeyValueArrayAdapter categoryAdapter = new KeyValueArrayAdapter(getContext(), android.R.layout.simple_spinner_item, categoryKeyValueList);
                KeyValueArrayAdapter weaponAdapter = new KeyValueArrayAdapter(getContext(), android.R.layout.simple_spinner_item, mainWeaponKeyValueList);

                //レイアウトを付与
                categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                weaponAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //スピナーにアダプターを設定
                categorySpinner.setAdapter(categoryAdapter);
                weaponSpinner.setAdapter(weaponAdapter);

                //リスナーを作成
                WeaponSpinnerSelectedListener categoryListener = new WeaponSpinnerSelectedListener(getContext() , weaponSpinner, mainWeaponKeyValueList);

                //リスナーを設定
                categorySpinner.setOnItemSelectedListener(categoryListener);
            }
        };
        task.execute();
    }

    /*
     * DB非同期処理を実行するクラス
     */
    private static class GetWeaponNameAsyncTask extends AsyncTask<Void, Void, Integer> {
        private AppDatabase db;
        List<MainCategory> categoryList;
        List<MainName> mainNameList;
        List<WeaponName> weaponNameList;
        List<WeaponMain> weaponMainList;
        //List<WeaponMainCategory> weaponList;

        static int languageCode = Util.getLanguageCode();

        public GetWeaponNameAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //端末の言語設定を取得
            int languageCode = Util.getLanguageCode();

            //実際にDBにアクセスし結果を取得
            //WeaponMainCategoryDao dao = db.weaponMainCategoryDao();
            MainCategoryDao categoryDao = db.mainCategoryDao();
            MainNameDao mainNameDao = db.mainNameDao();
            WeaponNameDao weaponNameDao = db.weaponNameDao();
            WeaponMainDao weaponMainDao = db.weaponMainDao();
            categoryList = categoryDao.getMainCategoryList(languageCode); //ブキカテゴリー名を取得
            mainNameList = mainNameDao.getMainNameList(languageCode); //ブキ種名を取得
            weaponNameList = weaponNameDao.getWeaponNameList(languageCode); //ブキ名を取得
            weaponMainList = weaponMainDao.getWeaponMainList(languageCode);
            //weaponList = dao.mainWeaponNameList(Util.getLanguageCode());

            return 0;
        }
    }
}