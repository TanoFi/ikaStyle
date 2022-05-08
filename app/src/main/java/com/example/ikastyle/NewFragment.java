package com.example.ikastyle;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;

import android.util.Pair;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ikastyle.Common.Const.GearPowerResourceId;
import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Dao.GearSetDao;
import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Dao.MainNameDao;
import com.example.ikastyle.Dao.WeaponMainDao;
import com.example.ikastyle.Dao.WeaponNameDao;
import com.example.ikastyle.Database.AppDatabase;
import com.example.ikastyle.DatabaseView.WeaponMain;
import com.example.ikastyle.Entity.GearSet;
import com.example.ikastyle.Entity.MainCategory;
import com.example.ikastyle.Entity.MainName;
import com.example.ikastyle.Entity.WeaponName;
import com.example.ikastyle.UI.GearPowerImageView;
import com.example.ikastyle.UI.GearPowerReceptorImageView;
import com.example.ikastyle.UI.GetDataAndSetSpinnerAsyncTask;
import com.example.ikastyle.UI.KeyValueArrayAdapter;
import com.example.ikastyle.UI.WeaponSpinnerSelectedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFragment extends Fragment{
    private Spinner categorySpinner;
    private Spinner weaponSpinner;
    private TextView gearSetName;
    private GearPowerReceptorImageView headMain;
    private GearPowerReceptorImageView headSub1;
    private GearPowerReceptorImageView headSub2;
    private GearPowerReceptorImageView headSub3;
    private GearPowerReceptorImageView clothingMain;
    private GearPowerReceptorImageView clothingSub1;
    private GearPowerReceptorImageView clothingSub2;
    private GearPowerReceptorImageView clothingSub3;
    private GearPowerReceptorImageView shoesMain;
    private GearPowerReceptorImageView shoesSub1;
    private GearPowerReceptorImageView shoesSub2;
    private GearPowerReceptorImageView shoesSub3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewFragment newInstance(String param1, String param2) {
        NewFragment fragment = new NewFragment();
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
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Spinnerの画面要素を取得
        categorySpinner = view.findViewById(R.id.spinner_category);
        weaponSpinner = view.findViewById(R.id.spinner_weapon);

        // textViewの画面要素を取得
        gearSetName = view.findViewById(R.id.textView_gearSetName);

        // GearPowerReceptorImageViewの画面要素を取得
        headMain = view.findViewById(R.id.imageView_head_main);
        headSub1 = view.findViewById(R.id.imageView_head_sub1);
        headSub2 = view.findViewById(R.id.imageView_head_sub2);
        headSub3 = view.findViewById(R.id.imageView_head_sub3);
        clothingMain = view.findViewById(R.id.imageView_clothing_main);
        clothingSub1 = view.findViewById(R.id.imageView_clothing_sub1);
        clothingSub2 = view.findViewById(R.id.imageView_clothing_sub2);
        clothingSub3 = view.findViewById(R.id.imageView_clothing_sub3);
        shoesMain = view.findViewById(R.id.imageView_shoes_main);
        shoesSub1 = view.findViewById(R.id.imageView_shoes_sub1);
        shoesSub2 = view.findViewById(R.id.imageView_shoes_sub2);
        shoesSub3 = view.findViewById(R.id.imageView_shoes_sub3);

        // Spinnerの項目に設定するためのDBを取得
        AppDatabase db = AppDatabase.getDatabase(getContext());
        // DBからデータを取得しスピナーにセット
        GetDataAndSetSpinnerAsyncTask task = new GetDataAndSetSpinnerAsyncTask(db, getContext(), categorySpinner, weaponSpinner);
        task.execute();

        FloatingActionButton saveButton = view.findViewById(R.id.floatingActionButton_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUserInput()){ //入力チェック
                    // DBに保存
                    save();
                }
            }
        });
    }

    /*
     * 入力チェック用のメソッド
     * 戻り値 true → 問題なし, false → 問題あり
     */
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
    private void save(){
        // 選択したブキの絶対ID
        int absoluteId = ((Pair<Integer, String>)weaponSpinner.getSelectedItem()).first;

        // Insert用のGearSetインスタンス作成
        GearSet gearSet = new GearSet(
                gearSetName.getText().toString(),
                Util.getCategoryId(absoluteId),
                Util.getMainId(absoluteId),
                Util.getCustomizationId(absoluteId),
                0, // ギアを選択する機能がないのでひとまず0
                headMain.getGearPowerKind(),
                headSub1.getGearPowerKind(),
                headSub2.getGearPowerKind(),
                headSub3.getGearPowerKind(),
                0, // ギアを選択する機能がないのでひとまず0
                clothingMain.getGearPowerKind(),
                clothingSub1.getGearPowerKind(),
                clothingSub2.getGearPowerKind(),
                clothingSub3.getGearPowerKind(),
                0, // ギアを選択する機能がないのでひとまず0
                shoesMain.getGearPowerKind(),
                shoesSub1.getGearPowerKind(),
                shoesSub2.getGearPowerKind(),
                shoesSub3.getGearPowerKind(),
                System.currentTimeMillis()
        );

        AppDatabase db = AppDatabase.getDatabase(getContext());
        InsertGearSetAsyncTask task = new InsertGearSetAsyncTask(db, gearSet);
        task.execute();
    }

    /*
     * 非同期でDBにGearSetデータをInsert
     */
    public class InsertGearSetAsyncTask extends AsyncTask<Void, Void, Integer> {
        private AppDatabase db;
        private GearSet gearSet;

        public InsertGearSetAsyncTask(AppDatabase db, GearSet gearSet) {
            this.db = db;
            this.gearSet = gearSet;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //実際にDBにアクセスし結果を取得
            GearSetDao gearSetDao = db.gearSetDao();
            gearSetDao.InsertGearSet(gearSet);

            return 0;
        }

        @Override
        protected void onPostExecute(Integer code){
            // ToastでInsert完了のメッセージ表示
            Toast toast = Toast.makeText(getContext(), getString(R.string.toastMessage_insertCompleted), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}