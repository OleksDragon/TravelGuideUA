package com.example.travelguideua.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.travelguideua.data.local.FavoritePlace;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    void insert(FavoritePlace place);

    @Delete
    void delete(FavoritePlace place);

    @Query("SELECT * FROM favorites")
    LiveData<List<FavoritePlace>> getAllFavorites();
}
