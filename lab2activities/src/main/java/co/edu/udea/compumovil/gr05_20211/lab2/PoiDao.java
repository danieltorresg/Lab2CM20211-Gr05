package co.edu.udea.compumovil.gr05_20211.lab2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PoiDao {

    @Insert
    void  registerPoi(PoiEntity poiEntity);

    @Query("SELECT * from pois where userId=(:userId)")
    List<PoiEntity> getPois(String userId);

    @Delete
    void delete(PoiEntity poiEntity);

    @Query("SELECT * from pois where name=(:name)")
    List<PoiEntity> getPoi(String name);

    @Update
    void update(PoiEntity poiEntity);


}
