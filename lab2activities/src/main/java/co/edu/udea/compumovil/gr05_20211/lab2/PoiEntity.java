package co.edu.udea.compumovil.gr05_20211.lab2;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "pois", foreignKeys = {
        @ForeignKey(entity = UserEntity.class,
                parentColumns = "userId",
                childColumns = "userId",
                onDelete = CASCADE)
}, indices = {@Index(value = {"userId"})})
public class PoiEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "description")
    String description;

    @ColumnInfo(name = "picture")
    String picture;

    @ColumnInfo(name = "rating")
    Float rating;

    @ColumnInfo(name = "userId")
    String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
