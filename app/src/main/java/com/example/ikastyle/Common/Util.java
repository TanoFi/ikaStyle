package com.example.ikastyle.Common;

import android.graphics.Color;
import android.util.Log;

import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.Common.Const.ResourceIdMap;

import java.util.Locale;
import java.util.Optional;
import java.util.Random;

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
     * ブキのカテゴリーID,メインID,カスタマイズIDから絶対IDを返す
     */
    public static int getAbsoluteId(int categoryId, int mainId, int customizationId){
        return categoryId * NumberPlace.CATEGORY_PLACE + mainId * NumberPlace.MAIN_PLACE + customizationId * NumberPlace.WEAPON_PLACE;
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

    /*
     * ギアパワーのIDを受け取って対応する画像のResourceIdを返す
     */
    public static int getGearPowerResourceId(int gearPowerId){
        return ResourceIdMap.gearPowerResourceIdMap.get(gearPowerId);
    }

    /*
     * アタマのギアIDを受け取って対応する画像のResourceIdを返す
     */
    public static int getHeadGearResourceId(int gearId){
        return ResourceIdMap.headGearResourceIdMap.get(gearId);
    }

    /*
     * フクのギアIDを受け取って対応する画像のResourceIdを返す
     */
    public static int getClothingResourceId(int gearId){
        return ResourceIdMap.clothingGearResourceIdMap.get(gearId);
    }

    /*
     * クツのギアIDを受け取って対応する画像のResourceIdを返す
     */

    public static int gerShoesResourceId(int gearId){
        return ResourceIdMap.shoesGearResourceIdMap.get(gearId);
    }

    /*
     * ブキの絶対IDを受け取って対応する画像のResourceIdを返す
     */
    public static int getWeaponResourceId(int absoluteId){
        return  ResourceIdMap.weaponResourceIdMap.get(absoluteId);
    }

    /*
     * ランダムな色を返す
     */
    public static int getRandomColor(){
        final int rgbNum = 256;

        Random random = new Random();
        int red = random.nextInt(rgbNum);
        int green = random.nextInt(rgbNum);
        int blue = random.nextInt(rgbNum);

        return Color.rgb(red, green, blue);
    }
}
