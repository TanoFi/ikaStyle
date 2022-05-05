package com.example.ikastyle;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.ikastyle.Common.Const.GearPowerResourceId;
import com.example.ikastyle.UI.GearPowerImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFragment extends Fragment implements View.OnTouchListener, View.OnDragListener{

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
    public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view, bundle);

        // ギアパワーのGridLayoutを取得
        GridLayout gearPowerGrid = view.findViewById(R.id.gridLayout_gearPower);

        // セルのGearPowerImageViewすべてにOnTouchリスナーをセット
        for(int i = 0; i < gearPowerGrid.getChildCount(); i++){
            GearPowerImageView gearPowerImageView = (GearPowerImageView) gearPowerGrid.getChildAt(i);
            gearPowerImageView.setOnTouchListener(this);
        }

        // ギアセットのGridLayoutを取得
        CardView cardView = view.findViewById(R.id.cardView_gearSet);

        for (int i = 0; i < cardView.getChildCount(); i++){
            GridLayout gearGrid = (GridLayout) cardView.getChildAt(i);

            for (int j = 0; j < gearGrid.getChildCount(); j++){
                View childView = gearGrid.getChildAt(j);
                if(childView instanceof GearPowerImageView){
                    childView.setOnDragListener(this);
                }
            }
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            view.startDragAndDrop(null, new View.DragShadowBuilder(view), view, 0);
        }

        return true;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent){
        if(dragEvent.getAction() == DragEvent.ACTION_DROP){
            GearPowerImageView dragView = (GearPowerImageView) dragEvent.getLocalState();

            int gearPowerKind = dragView.getGearPowerKind();
            // フィールド変数gearPowerKindをセットしながら、セットしたKindのギア画像に差し替え
            ((GearPowerImageView)view).setGearPowerKind(gearPowerKind);
        }
        return true;
    }
}