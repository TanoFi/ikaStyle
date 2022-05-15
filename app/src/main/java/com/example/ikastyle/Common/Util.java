package com.example.ikastyle.Common;

import android.graphics.Color;

import com.example.ikastyle.Common.Const.NumberPlace;
import com.example.ikastyle.Common.Const.ResourceIdMap;
import com.example.ikastyle.R;

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
        if(local.equals(Locale.JAPAN)){
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
        return Optional.ofNullable(ResourceIdMap.gearPowerResourceIdMap.get(gearPowerId)).orElse(0);
    }

    /*
     * アタマのギアIDを受け取って対応する画像のResourceIdを返す
     */
    public static int getHeadGearResourceId(int gearId){
        return Optional.ofNullable(ResourceIdMap.headGearResourceIdMap.get(gearId)).orElse(R.drawable.headgear0);
    }

    /*
     * フクのギアIDを受け取って対応する画像のResourceIdを返す
     */
    public static int getClothingResourceId(int gearId){
        return Optional.ofNullable(ResourceIdMap.clothingGearResourceIdMap.get(gearId)).orElse(R.drawable.clothing_gear0);
    }

    /*
     * クツのギアIDを受け取って対応する画像のResourceIdを返す
     */

    public static int getShoesResourceId(int gearId){
        return Optional.ofNullable(ResourceIdMap.shoesGearResourceIdMap.get(gearId)).orElse(R.drawable.shoes_gear0);
    }

    /*
     * ブキの絶対IDを受け取って対応する画像のResourceIdを返す
     */
    public static int getWeaponResourceId(int absoluteId){
        return  Optional.ofNullable(ResourceIdMap.weaponResourceIdMap.get(absoluteId)).orElse(R.drawable.weapon1011_sploosh_o_matic);
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
