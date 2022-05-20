package jp.java_conf.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import jp.java_conf.ikastyle.Entity.ClothingGear;

import java.util.List;

@Dao
public interface ClothingGearDao {
    @Query("SELECT * FROM MAST_CLOTHING_GEAR WHERE language_code = :languageCode ORDER BY name")
    public List<ClothingGear> getClothingGearList(int languageCode);
}
