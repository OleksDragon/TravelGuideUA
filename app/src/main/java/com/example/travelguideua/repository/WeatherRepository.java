package com.example.travelguideua.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.util.Log;

import com.example.travelguideua.data.model.WeatherResponse;
import com.example.travelguideua.data.remote.WeatherApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "09d72de26f323bd2567abd32f58ec26c";
    private WeatherApiService apiService;

    public WeatherRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(WeatherApiService.class);
    }

    public LiveData<WeatherResponse> getWeather(String city) {
        MutableLiveData<WeatherResponse> liveData = new MutableLiveData<>();
        apiService.getWeather(city, API_KEY, "metric").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(response.body());
                } else {
                    Log.e("WeatherRepository", "Ошибка ответа API: " + response.code());
                    liveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("WeatherRepository", "Ошибка сети", t);
                liveData.setValue(null);
            }
        });
        return liveData;
    }
}
