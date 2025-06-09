package com.example.travelguideua.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.travelguideua.data.db.AppDatabase;
import com.example.travelguideua.data.db.PlaceDao;
import com.example.travelguideua.data.model.Place;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlaceRepository {
    private final PlaceDao dao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public PlaceRepository(Application app) {
        AppDatabase db = AppDatabase.getInstance(app);
        dao = db.placeDao();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return dao.getAll();
    }

    public LiveData<List<Place>> searchPlaces(String query) {
        return dao.search("%" + query + "%");
    }

    public LiveData<Place> getPlaceById(String placeId) {
        return dao.getPlaceById(placeId);
    }

    public void insert(Place place) {
        executor.execute(() -> dao.insert(place));
    }

    public void update(Place place) {
        executor.execute(() -> dao.update(place));
    }

    public void delete(Place place) {
        executor.execute(() -> dao.delete(place));
    }
}

