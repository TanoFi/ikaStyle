package com.example.ikastyle;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ikastyle.Common.Const.GearKind;
import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Dao.CustomizationMainDao;
import com.example.ikastyle.Dao.LoadoutDao;
import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Database.AppDatabase;
import com.example.ikastyle.DatabaseView.CustomizationMain;
import com.example.ikastyle.Entity.Loadout;
import com.example.ikastyle.Entity.MainCategory;
import com.example.ikastyle.UI.CategorySpinnerSelectedListener;
import com.example.ikastyle.UI.KeyValueArrayAdapter;
import com.example.ikastyle.UI.GearDialogFragment;
import com.example.ikastyle.UI.GearImageView;
import com.example.ikastyle.UI.GearPowerReceptorImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class NewFragment extends Fragment implements GearDialogFragment.GearDialogListener{
    private Spinner categorySpinner;
    private Spinner weaponSpinner;
    private EditText loadoutName;
    private GearImageView headGear;
    private GearPowerReceptorImageView headMain;
    private GearPowerReceptorImageView headSub1;
    private GearPowerReceptorImageView headSub2;
    private GearPowerReceptorImageView headSub3;
    private GearImageView clothingGear;
    private GearPowerReceptorImageView clothingMain;
    private GearPowerReceptorImageView clothingSub1;
    private GearPowerReceptorImageView clothingSub2;
    private GearPowerReceptorImageView clothingSub3;
    private GearImageView shoesGear;
    private GearPowerReceptorImageView shoesMain;
    private GearPowerReceptorImageView shoesSub1;
    private GearPowerReceptorImageView shoesSub2;
    private GearPowerReceptorImageView shoesSub3;

    // GearImageViewをクリックしたときの処理
    private static View.OnClickListener onClickGearImageView;

    public NewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // GearImageViewをクリックした時の処理を定義
        onClickGearImageView = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GearDialogFragment gearDialogFragment = new GearDialogFragment(((GearImageView)view).getGearKind());
                gearDialogFragment.show(getChildFragmentManager(), "gear_dialog");
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Spinnerの画面要素を取得
        categorySpinner = view.findViewById(R.id.spinner_category);
        weaponSpinner = view.findViewById(R.id.spinner_weapon);

        // textViewの画面要素を取得
        loadoutName = view.findViewById(R.id.editText_loadoutName);

        // GearPowerReceptorImageViewの画面要素を取得
        headMain = view.findViewById(R.id.receptorImageView_head_main);
        headSub1 = view.findViewById(R.id.receptorImageView_head_sub1);
        headSub2 = view.findViewById(R.id.receptorImageView_head_sub2);
        headSub3 = view.findViewById(R.id.receptorImageView_head_sub3);
        clothingMain = view.findViewById(R.id.receptorImageView_clothing_main);
        clothingSub1 = view.findViewById(R.id.receptorImageView_clothing_sub1);
        clothingSub2 = view.findViewById(R.id.receptorImageView_clothing_sub2);
        clothingSub3 = view.findViewById(R.id.receptorImageView_clothing_sub3);
        shoesMain = view.findViewById(R.id.receptorImageView_shoes_main);
        shoesSub1 = view.findViewById(R.id.receptorImageView_shoes_sub1);
        shoesSub2 = view.findViewById(R.id.receptorImageView_shoes_sub2);
        shoesSub3 = view.findViewById(R.id.receptorImageView_shoes_sub3);

        // Spinnerの項目に設定するためのDBを取得
        AppDatabase db = AppDatabase.getDatabase(getContext());
        // DBからデータを取得しスピナーにセット
        GetDataAndSetSpinnerAsyncTask task = new GetDataAndSetSpinnerAsyncTask(db, getContext(), categorySpinner, weaponSpinner);
        task.execute();

        FloatingActionButton saveButton = view.findViewById(R.id.floatingActionButton_save);
        saveButton.setOnClickListener(x ->{
            if(checkUserInput()){ //入力チェック
                // DBに保存
                save();
            }
        });

        // GearImageViewの画面要素を取得
        headGear = view.findViewById(R.id.gearImageView_head);
        clothingGear = view.findViewById(R.id.gearImageView_clothing);
        shoesGear = view.findViewById(R.id.gearImageView_shoes);

        // GearImageViewにonClickListenerをまとめてセット
        setOnClickListener(headGear, clothingGear, shoesGear);
    }

    @Override
    public void onListItemClick(GearDialogFragment dialogFragment, GearKind gearKind, int gearId){
        switch (gearKind){
            case HEAD:
                headGear.setGearId(gearId);
                break;
            case CLOTHING:
                clothingGear.setGearId(gearId);
                break;
            case SHOES:
                shoesGear.setGearId(gearId);
                break;
        }
    }

    /*
     * 入力チェック用のメソッド
     * 戻り値 true → 問題なし, false → 問題あり
     */
    @SuppressWarnings("unchecked")
    private boolean checkUserInput(){
        // ブキSpinnerが未選択状態
        if(((Pair<Integer, String>)weaponSpinner.getSelectedItem()).first == 0){
            // Toastでメッセージ表示
            Toast toast = Toast.makeText(getContext(), getString(R.string.toastMessage_spinnerNotSelected), Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        // メインギアパワーが設定されていない
        if(headMain.getGearPowerKind() == 0 || clothingMain.getGearPowerKind() == 0 || shoesMain.getGearPowerKind() == 0){
            // Toastでメッセージ表示
            Toast toast = Toast.makeText(getContext(), getString(R.string.toastMessage_mainGearPowerNotSet), Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        return true;
    }

    /*
     * 入力値をデータベースに保存
     */
    @SuppressWarnings("unchecked")
    private void save(){
        // 選択したブキの絶対ID
        int absoluteId = ((Pair<Integer, String>)weaponSpinner.getSelectedItem()).first;

        // Insert用のLoadoutインスタンス作成
        Loadout loadout = new Loadout(
                loadoutName.getText().toString(),
                Util.getCategoryId(absoluteId),
                Util.getMainId(absoluteId),
                Util.getCustomizationId(absoluteId),
                headGear.getGearId(),
                headMain.getGearPowerKind(),
                headSub1.getGearPowerKind(),
                headSub2.getGearPowerKind(),
                headSub3.getGearPowerKind(),
                clothingGear.getGearId(),
                clothingMain.getGearPowerKind(),
                clothingSub1.getGearPowerKind(),
                clothingSub2.getGearPowerKind(),
                clothingSub3.getGearPowerKind(),
                shoesGear.getGearId(),
                shoesMain.getGearPowerKind(),
                shoesSub1.getGearPowerKind(),
                shoesSub2.getGearPowerKind(),
                shoesSub3.getGearPowerKind(),
                System.currentTimeMillis()
        );

        AppDatabase db = AppDatabase.getDatabase(getContext());
        InsertLoadoutAsyncTask task = new InsertLoadoutAsyncTask(db, loadout);
        task.execute();
    }

    /*
     * GearImageViewにonClickListenerをまとめてセットする
     */
    private void setOnClickListener(GearImageView... gearImageViews){
        for (GearImageView gearImageView: gearImageViews) {
            gearImageView.setOnClickListener(onClickGearImageView);
        }
    }

    // View初期化用メソッド
    private void init(){
        // Spinner選択項目初期化
        categorySpinner.setSelection(0);
        weaponSpinner.setSelection(0);

        // TextView文字入力初期化
        loadoutName.setText("");

        // GearImageView選択項目初期化
        headGear.init();
        clothingGear.init();
        shoesGear.init();

        // ReceptorImageView設定ギア初期化
        headMain.init();
        headSub1.init();
        headSub2.init();
        headSub3.init();
        clothingMain.init();
        clothingSub1.init();
        clothingSub2.init();
        clothingSub3.init();
        shoesMain.init();
        shoesSub1.init();
        shoesSub2.init();
        shoesSub3.init();
    }

    /*
     * 非同期でDBにLoadoutデータをInsert
     */
    private class InsertLoadoutAsyncTask extends AsyncTask<Void, Void, Integer> {
        private final AppDatabase db;
        private final Loadout loadout;

        public InsertLoadoutAsyncTask(AppDatabase db, Loadout loadout) {
            this.db = db;
            this.loadout = loadout;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //実際にDBにアクセスし結果を取得
            LoadoutDao loadoutDao = db.loadoutDao();
            loadoutDao.InsertLoadout(loadout);

            return 0;
        }

        @Override
        protected void onPostExecute(Integer code){
            // Viewを初期化
            init();

            // ToastでInsert完了のメッセージ表示
            Toast toast = Toast.makeText(getContext(), getString(R.string.toastMessage_insertCompleted), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /*
     * 非同期でDBからデータ取得しスピナーにセットするクラス
     */
    private class GetDataAndSetSpinnerAsyncTask extends AsyncTask<Void, Void, Integer> {
        private final AppDatabase db;
        private final Context context;
        private final Spinner categorySpinner;
        private final Spinner weaponSpinner;

        List<MainCategory> categoryList;
        List<CustomizationMain> customizationMainList;

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
            CustomizationMainDao customizationMainDao = db.customizationMainDao();
            categoryList = categoryDao.getMainCategoryList(languageCode); //ブキカテゴリー名を取得
            customizationMainList = customizationMainDao.getWeaponMainList(languageCode);

            return 0;
        }

        @Override
        protected void onPostExecute(Integer code){
            // ひとつのメインウェポン : そのメインウェポンを持つブキセットのリスト でMapを作成
            // 昇順でソートしたいのでTreeMap
            TreeMap<Integer, List<CustomizationMain>> customizationMainMap = new TreeMap<>(customizationMainList.stream().collect(
                    Collectors.groupingBy(x -> x.categoryId * NumberPlace.CATEGORY_PLACE + x.mainId * NumberPlace.MAIN_PLACE)
            ));

            // Spinnerに渡す用のリストを作成
            ArrayList<Pair<Integer, String>> categoryKeyValueList = new ArrayList<>();
            ArrayList<Pair<Integer, String>> mainAndCustomizationKeyValueList = new ArrayList<>();

            // それぞれのリストの一番上に未選択時の項目を追加
            categoryKeyValueList.add(new Pair<>(0, context.getString(R.string.spinnerItem_categoryUnselected)));
            mainAndCustomizationKeyValueList.add(new Pair<>(0, context.getString(R.string.spinnerItem_weaponUnselected)));

            //それぞれのリストにデータ(IDと名前のペア)を入れる
            categoryList.forEach(x -> categoryKeyValueList.add(new Pair<>(x.getAbsoluteId(), x.getName())));
            for (Map.Entry<Integer, List<CustomizationMain>> data :customizationMainMap.entrySet()) {
                int key = data.getKey();
                List<CustomizationMain> value = data.getValue();
                mainAndCustomizationKeyValueList.add(new Pair<>(key, value.get(0).getMainName())); //メインウェポンのデータをAdd

                value.forEach(x -> mainAndCustomizationKeyValueList.add(new Pair<>(x.getAbsoluteId(), x.getWeaponName()))); //ブキセットのデータをAdd
            }

            //アダプター作成
            KeyValueArrayAdapter categoryAdapter = new KeyValueArrayAdapter(context, R.layout.spinner_list_item, categoryKeyValueList);
            KeyValueArrayAdapter weaponAdapter = new KeyValueArrayAdapter(context, R.layout.spinner_list_item, mainAndCustomizationKeyValueList);

            //レイアウトを付与
            categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item);
            weaponAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item);

            //スピナーにアダプターを設定
            categorySpinner.setAdapter(categoryAdapter);
            weaponSpinner.setAdapter(weaponAdapter);

            //リスナーを作成
            CategorySpinnerSelectedListener categoryListener = new CategorySpinnerSelectedListener(context , weaponSpinner, mainAndCustomizationKeyValueList);

            //リスナーを設定
            categorySpinner.setOnItemSelectedListener(categoryListener);
        }
    }
}