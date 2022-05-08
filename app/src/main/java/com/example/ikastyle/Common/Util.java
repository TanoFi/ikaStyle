package com.example.ikastyle.Common;

import com.example.ikastyle.Common.Const.NumberPlace;

import java.util.Locale;

public class Util {
    /*
     * 端末の設定が日本語なら2、それ以外なら1を返す
     */
    public static int getLanguageCode()
    {
        Locale local = Locale.getDefault();
        if(local.equals(Locale.JAPAN) || local.getLanguage().equals(Locale.JAPANESE)){
            return  2;
        }
        else {
            return 1;
        }
    }

    /*
     * ブキの絶対IDを受け取ってカテゴリーIDを返す
     */
    public static int getCategoryId(int absoluteId){
        return absoluteId / NumberPlace.CATEGORY_PLACE;
    }

    /*
     * ブキの絶対IDを受け取ってメインIDを返す
     */
    public static int getMainId(int absoluteId){
        return (absoluteId % NumberPlace.CATEGORY_PLACE) / NumberPlace.MAIN_PLACE;
    }

    /*
     * ブキの絶対IDを受け取ってカスタマイズIDを返す
     */
    public static int getCustomizationId(int absoluteId){
        return (absoluteId % NumberPlace.CATEGORY_PLACE) % NumberPlace.MAIN_PLACE;
    }
}
