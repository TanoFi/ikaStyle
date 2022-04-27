package com.example.ikastyle.DatabaseView;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.Query;

@DatabaseView(
        viewName = "WEAPON_MAIN_CATEGORY_NAME",
        value = "SELECT \n" +
                "\tWN.id AS weapon_id, \n" +
                "\tWN.main_id,\n" +
                "\tWN.category_id, \n" +
                "\tWN.language_code, \n" +
                "\tWN.name AS weapon_name, \n" +
                "\tMN.name AS main_name,\n" +
                "\tMC.name AS category_name\n" +
                "FROM MAST_WEAPON_NAME WN\n" +
                "INNER JOIN MAST_MAIN_NAME MN\n" +
                "ON MN.id = WN.main_id\n" +
                "AND MN.category_id = WN.category_id\n" +
                "AND MN.language_code = WN.language_code\n" +
                "INNER JOIN MAST_MAIN_CATEGORY MC\n" +
                "ON MC.id = WN.category_id \n" +
                "AND MC.language_code = WN.language_code\n"
)
public class WeaponMainCategory {
    @ColumnInfo(name = "weapon_id")
    public int weaponId;

    @ColumnInfo(name = "main_id")
    public int mainId;

    @ColumnInfo(name = "category_id")
    public int categoryId;

    @ColumnInfo(name = "language_code")
    public int languageCode;

    @ColumnInfo(name = "weapon_name")
    public String weaponName;

    @ColumnInfo(name = "main_name")
    public String mainName;

    @ColumnInfo(name = "category_name")
    public String categoryName;
}
