package com.example.ikastyle;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

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

import com.example.ikastyle.Common.Const.GearPowerResourceId;
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
import com.example.ikastyle.UI.GearPowerImageView;
import com.example.ikastyle.UI.GetDataAndSetSpinnerAsyncTask;
import com.example.ikastyle.UI.KeyValueArrayAdapter;
import com.example.ikastyle.UI.WeaponSpinnerSelectedListener;

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

        //スピナーを取得
        Spinner categorySpinner = view.findViewById(R.id.spinner_category);
        Spinner weaponSpinner = view.findViewById(R.id.spinner_weapon);

        // Spinnerの項目に設定するためのDBを取得
        AppDatabase db = AppDatabase.getDatabase(getContext());
        // DBからデータを取得しスピナーにセット
        GetDataAndSetSpinnerAsyncTask task = new GetDataAndSetSpinnerAsyncTask(db, getContext(), categorySpinner, weaponSpinner);
        task.execute();
    }
}