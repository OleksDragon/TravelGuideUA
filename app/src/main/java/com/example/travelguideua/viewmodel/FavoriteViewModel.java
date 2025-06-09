package com.example.travelguideua.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelguideua.data.local.FavoritePlace;
import com.example.travelguideua.repository.FavoriteRepository;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private final FavoriteRepository favoriteRepository;
    private final LiveData<List<FavoritePlace>> allFavorites;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        favoriteRepository = new FavoriteRepository(application);
        allFavorites = favoriteRepository.getAllFavorites();
    }

    public LiveData<List<FavoritePlace>> getAllFavorites() {
        return allFavorites;
    }

    public void insert(FavoritePlace favoritePlace) {
        favoriteRepository.insert(favoritePlace);
    }

    public void delete(FavoritePlace favoritePlace) {
        favoriteRepository.delete(favoritePlace);
    }
}
