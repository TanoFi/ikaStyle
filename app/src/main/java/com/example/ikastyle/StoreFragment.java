package com.example.ikastyle;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ikastyle.Common.Const.DatabaseName;
import com.example.ikastyle.Common.Util;
import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Database.AppDatabase;

import java.util.List;

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
                Integer[] numbers = new Integer[]{1,3,5,7};
                ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, weaponList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //spinnerにadapterを設定
                Spinner weaponSpinner = view.findViewById(R.id.spinner_weapon);
                weaponSpinner.setAdapter(adapter);
            }
        };
        task.execute();
    }

    /*
     * DB非同期処理を実行するクラス
     */
    private static class GetWeaponNameAsyncTask extends AsyncTask<Void, Void, Integer> {
        private AppDatabase db;
        List<String> weaponList;

        public GetWeaponNameAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //実際にDBにアクセスし結果を取得
            MainCategoryDao dao = db.mainCategoryDao();
            weaponList = dao.mainCategoryNames(Util.getLanguageCode());

            return 0;
        }
    }
}