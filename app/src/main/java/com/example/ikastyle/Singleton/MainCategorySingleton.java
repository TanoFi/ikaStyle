package com.example.ikastyle.Singleton;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.ikastyle.Database.MainCategoryDatabase;
import com.example.ikastyle.Entity.MainCategory;

import java.util.ArrayList;
import java.util.List;

public class MainCategorySingleton {
    private static MainCategoryDatabase instance = null;
    private MainCategorySingleton(){}

    public static MainCategoryDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, MainCategoryDatabase.class, "main_category").build();

            //インスタンス作成時だけ初期データを入れる
            //↓↓↓ 改修予定箇所 : 初期データ投入が手動のInsertだから
            inputInitialData().forEach(entity -> instance.mainCategoryDao().insertAll(entity));
            //↑↑↑ 改修予定箇所
        }
        return instance;
    }

    //↓↓↓ 削除予定箇所
    @NonNull
    private static List<MainCategory> inputInitialData(){
        List<MainCategory> mainCategoryList = new ArrayList<MainCategory>();
        mainCategoryList.add(new MainCategory(1,1,"Shooters"));
        mainCategoryList.add(new MainCategory(1,2,"シューター"));
        mainCategoryList.add(new MainCategory(2,1,"Blasters"));
        mainCategoryList.add(new MainCategory(2,2,"ブラスター"));

        return mainCategoryList;
    }
    //↑↑↑ 削除予定箇所
}
