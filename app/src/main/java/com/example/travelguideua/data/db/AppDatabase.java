package com.example.travelguideua.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.travelguideua.data.local.FavoritePlace;
import com.example.travelguideua.data.local.HistoryPlace;
import com.example.travelguideua.data.model.Place;

@Database(entities = {Place.class, FavoritePlace.class, HistoryPlace.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract FavoriteDao favoriteDao();
    public abstract HistoryDao historyDao();
    public abstract PlaceDao placeDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "travel_guide_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
