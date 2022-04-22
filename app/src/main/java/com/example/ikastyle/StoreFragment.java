package com.example.ikastyle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ikastyle.Database.MainCategoryDatabase;
import com.example.ikastyle.Singleton.MainCategorySingleton;
import com.example.ikastyle.Util.Util;

import java.util.List;

import io.reactivex.Completable;

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

        // Spinnerに項目設定
        MainCategoryDatabase db = Room.databaseBuilder(getContext(), MainCategoryDatabase.class, "MastMainCategory.db").build();

//        Handler handler = new Handler(Looper.getMainLooper());
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        })
//        Completable.fromAction( db.mainCategoryDao().mainCategoryNames(Util.getLanguageCode()))
//        .sub
        List<String> weaponList = db.mainCategoryDao().mainCategoryNames(Util.getLanguageCode());

//        Spinner weaponSpinner = (Spinner) view.findViewById(R.id.spinner_Weapon);
//        ArrayAdapter<CharSequence> apapter = ArrayAdapter.createFromResource(getContext(), weaponList, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
    }
}