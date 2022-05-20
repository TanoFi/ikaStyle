package jp.java_conf.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import jp.java_conf.ikastyle.Entity.HeadGear;

import java.util.List;

@Dao
public interface HeadGearDao {
    @Query("SELECT * FROM MAST_HEAD_GEAR WHERE language_code = :languageCode ORDER BY name")
    List<HeadGear> getHeadGearList(int languageCode);
}
