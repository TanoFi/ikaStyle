package com.example.ikastyle.DatabaseView;

import androidx.room.DatabaseView;

@DatabaseView(
        viewName = "WEAPON_MAIN_CATEGORY_NAME",
        value = "SELECT \n" +
                "\tWN.id, \n" +
                "\tWN.main_id,\n" +
                "\tWN.category_id, \n" +
                "\tWN.language_code, \n" +
                "\tWN.name, \n" +
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
    public int weaponId;
    public int mainId;
    public int categoryId;
    public int languageCode;
    public String weaponName;
    public String mainName;
    public String categoryName;
}
