package com.example.travelguideua.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.travelguideua.data.model.Place;

import java.util.List;

@Dao
public interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Place place);

    @Update
    void update(Place place);

    @Delete
    void delete(Place place);

    @Query("SELECT * FROM places ORDER BY name ASC")
    LiveData<List<Place>> getAll();

    @Query("SELECT * FROM places WHERE name LIKE :query")
    LiveData<List<Place>> search(String query);

    @Query("SELECT * FROM places WHERE id = :placeId")
    LiveData<Place> getPlaceById(String placeId);
}


