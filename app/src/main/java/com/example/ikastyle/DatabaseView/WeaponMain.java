package com.example.ikastyle.DatabaseView;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

import com.example.ikastyle.Common.Const.NumberPlace;

/*
 * MAST_MAIN_NAMEとMAST_WEAPON_NAMEの結合テーブルを取得するView
 */
@DatabaseView(
        viewName = "WEAPON_MAIN_NAME",
        value = "SELECT \n" +
                "WN.category_id,\n" +
                "WN.main_id,\n" +
                "WN.id as weapon_id,\n" +
                "WN.language_code, \n" +
                "MN.name as main_name,\n" +
                "WN.name as weapon_name\n" +
                "FROM MAST_MAIN_NAME MN\n" +
                "INNER JOIN MAST_WEAPON_NAME WN\n" +
                "ON WN.category_id = MN.category_id\n" +
                "AND WN.main_id = MN.id \n" +
                "AND WN.language_code = MN.language_code"
)
public class WeaponMain {
    @ColumnInfo(name = "category_id")
    public int categoryId;

    @ColumnInfo(name = "main_id")
    public int mainId;

    @ColumnInfo(name = "weapon_id")
    public int weaponId;

    @ColumnInfo(name = "language_code")
    public int languageCode;

    @ColumnInfo(name = "main_name")
    public String mainName;

    @ColumnInfo(name = "weapon_name")
    public String weaponName;

    public String getMainName(){
        return "【" + mainName + "】";
    }

    public int getAbsoluteId(){
        return  categoryId * NumberPlace.CATEGORY_PLACE + mainId * NumberPlace.MAIN_PLACE + weaponId;
    }

    public String getWeaponName(){
        return weaponName;
    }
}
