package com.example.travelguideua.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.travelguideua.data.model.Place;
import com.example.travelguideua.data.model.WeatherData;
import com.example.travelguideua.repository.PlaceRepository;
import com.example.travelguideua.repository.WeatherRepository;

import java.util.List;

public class PlaceViewModel extends AndroidViewModel {
    private final PlaceRepository repository;
    private final LiveData<List<Place>> allPlaces;
    private final WeatherRepository weatherRepository;
    private final MutableLiveData<WeatherData> weatherLiveData = new MutableLiveData<>();

    public PlaceViewModel(@NonNull Application application) {
        super(application);
        repository = new PlaceRepository(application);
        allPlaces = repository.getAllPlaces();
        weatherRepository = new WeatherRepository();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    public void insert(Place place) {
        repository.insert(place);
    }

    public void update(Place place) {
        repository.update(place);
    }

    public LiveData<List<Place>> search(String query) {
        return repository.searchPlaces(query);
    }

    public LiveData<Place> getPlaceById(String placeId) {
        return repository.getPlaceById(placeId);
    }

    // 👉 метод для загрузки погоды
    public void loadWeather(String city) {
        weatherRepository.getWeather(city).observeForever(response -> {
            if (response != null) {
                WeatherData data = new WeatherData(
                        response.getWeather().get(0).getDescription(),
                        response.getMain().getTemp(),
                        response.getMain().getFeelsLike(),
                        response.getMain().getTempMin(),
                        response.getMain().getTempMax(),
                        response.getMain().getHumidity(),
                        response.getMain().getPressure(),
                        response.getWind().getSpeed(),
                        response.getClouds().getAll(),
                        response.getSys().getSunrise(),
                        response.getSys().getSunset()
                );
                weatherLiveData.setValue(data);
            } else {
                weatherLiveData.setValue(null);
            }
        });
    }

    public LiveData<WeatherData> getWeatherLiveData() {
        return weatherLiveData;
    }
}



