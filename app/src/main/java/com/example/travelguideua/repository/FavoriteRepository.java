package com.example.travelguideua.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;

import com.example.travelguideua.data.db.AppDatabase;
import com.example.travelguideua.data.db.FavoriteDao;
import com.example.travelguideua.data.local.FavoritePlace;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteRepository {
    private FavoriteDao favoriteDao;
    private LiveData<List<FavoritePlace>> allFavorites;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public FavoriteRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        favoriteDao = db.favoriteDao();
        allFavorites = favoriteDao.getAllFavorites();
    }

    public LiveData<List<FavoritePlace>> getAllFavorites() {
        return allFavorites;
    }

    public void insert(FavoritePlace favoritePlace) {
        executor.execute(() -> favoriteDao.insert(favoritePlace));
    }

    public void delete(FavoritePlace favoritePlace) {
        executor.execute(() -> favoriteDao.delete(favoritePlace));
    }
}
