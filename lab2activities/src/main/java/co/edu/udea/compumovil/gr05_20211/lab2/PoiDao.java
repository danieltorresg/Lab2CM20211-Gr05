package co.edu.udea.compumovil.gr05_20211.lab2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PoiDao {

    @Insert
    void  registerPoi(PoiEntity poiEntity);

    @Query("SELECT * from pois where userId=(:userId)")
    List<PoiEntity> getPois(String userId);
}
