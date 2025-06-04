package com.example.travelguideua.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.travelguideua.data.db.PlaceDao;
import com.example.travelguideua.data.db.PlaceDatabase;
import com.example.travelguideua.data.model.Place;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlaceRepository {
    private final PlaceDao dao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public PlaceRepository(Application app) {
        PlaceDatabase db = PlaceDatabase.getInstance(app);
        dao = db.placeDao();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return dao.getAll();
    }

    public LiveData<List<Place>> searchPlaces(String query) {
        return dao.search("%" + query + "%");
    }

    public void insert(Place place) {
        executor.execute(() -> dao.insert(place));
    }

    public void update(Place place) {
        executor.execute(() -> dao.update(place));
    }
}

