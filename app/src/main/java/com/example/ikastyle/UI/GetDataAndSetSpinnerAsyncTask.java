package com.example.ikastyle.UI;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Spinner;

import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Dao.WeaponMainDao;
import com.example.ikastyle.Database.AppDatabase;
import com.example.ikastyle.DatabaseView.WeaponMain;
import com.example.ikastyle.Entity.MainCategory;
import com.example.ikastyle.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/*
 * 非同期でDBからデータ取得しスピナーにセットするクラス
 */
public class GetDataAndSetSpinnerAsyncTask extends AsyncTask<Void, Void, Integer> {
    private AppDatabase db;
    private Context context;
    private Spinner categorySpinner;
    private Spinner weaponSpinner;

    List<MainCategory> categoryList;
    List<WeaponMain> weaponMainList;

    public GetDataAndSetSpinnerAsyncTask(AppDatabase db, Context context, Spinner categorySpinner, Spinner weaponSpinner) {
        this.db = db;
        this.context = context;
        this.categorySpinner = categorySpinner;
        this.weaponSpinner = weaponSpinner;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        //端末の言語設定を取得
        int languageCode = Util.getLanguageCode();

        //実際にDBにアクセスし結果を取得
        MainCategoryDao categoryDao = db.mainCategoryDao();
        WeaponMainDao weaponMainDao = db.weaponMainDao();
        categoryList = categoryDao.getMainCategoryList(languageCode); //ブキカテゴリー名を取得
        weaponMainList = weaponMainDao.getWeaponMainList(languageCode);

        return 0;
    }

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
        categoryKeyValueList.add(new Pair<>(0, context.getString(R.string.spinnerItem_categoryUnselected)));
        mainWeaponKeyValueList.add(new Pair<>(0, context.getString(R.string.spinnerItem_weaponUnselected)));

        //それぞれのリストにデータ(IDと名前のペア)を入れる
        categoryList.forEach(x -> categoryKeyValueList.add(new Pair<>(x.getAbsoluteId(), x.getName())));
        for (Map.Entry<Integer, List<WeaponMain>> data :weaponMainMap.entrySet()) {
            int key = data.getKey();
            List<WeaponMain> value = data.getValue();
            mainWeaponKeyValueList.add(new Pair<>(key, value.get(0).getMainName())); //メインウェポンのデータをAdd

            value.forEach(x -> mainWeaponKeyValueList.add(new Pair<>(x.getAbsoluteId(), x.getWeaponName()))); //ブキセットのデータをAdd
        }

        //アダプター作成
        KeyValueArrayAdapter categoryAdapter = new KeyValueArrayAdapter(context, android.R.layout.simple_spinner_item, categoryKeyValueList);
        KeyValueArrayAdapter weaponAdapter = new KeyValueArrayAdapter(context, android.R.layout.simple_spinner_item, mainWeaponKeyValueList);

        //レイアウトを付与
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weaponAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //スピナーにアダプターを設定
        categorySpinner.setAdapter(categoryAdapter);
        weaponSpinner.setAdapter(weaponAdapter);

        //リスナーを作成
        CategorySpinnerSelectedListener categoryListener = new CategorySpinnerSelectedListener(context , weaponSpinner, mainWeaponKeyValueList);

        //リスナーを設定
        categorySpinner.setOnItemSelectedListener(categoryListener);
    }
}
