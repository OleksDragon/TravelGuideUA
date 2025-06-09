package com.example.travelguideua.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.travelguideua.data.local.HistoryPlace;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    void insert(HistoryPlace historyPlace);

    @Query("SELECT * FROM history ORDER BY timestamp DESC")
    LiveData<List<HistoryPlace>> getAllHistory();

    @Query("DELETE FROM history WHERE placeId = :placeId")
    void deleteHistoryPlace(String placeId);

    @Query("DELETE FROM history")
    void clearHistory();
}
