package com.example.travelguideua.ui.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.example.travelguideua.R;
import com.example.travelguideua.viewmodel.FavoriteViewModel;
import com.example.travelguideua.viewmodel.HistoryViewModel;
import com.example.travelguideua.viewmodel.PlaceViewModel;
import com.example.travelguideua.data.model.Place;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    private PlaceViewModel placeViewModel;
    private FavoriteViewModel favoriteViewModel;
    private HistoryViewModel historyViewModel;

    private TextView nameView, regionView, descriptionView, weatherView;
    private ImageView imageView, favoriteButton;

    private static final Map<String, String> regionCityMap = new HashMap<>();
    static {
        regionCityMap.put("Київська область", "Kyiv");
        regionCityMap.put("Львівська область", "Lviv");
        regionCityMap.put("Одеська область", "Odesa");
        regionCityMap.put("Івано-Франківська область", "Ivano-Frankivsk");
        regionCityMap.put("Харківська область", "Kharkiv");
        regionCityMap.put("Дніпропетровська область", "Dnipro");
        regionCityMap.put("Запорізька область", "Zaporizhzhia");
        regionCityMap.put("Хмельницька область", "Khmelnytskyi");
        regionCityMap.put("Полтавська область", "Poltava");
        regionCityMap.put("Чернігівська область", "Chernihiv");
        regionCityMap.put("Черкаська область", "Cherkasy");
        regionCityMap.put("Волинська область", "Lutsk");
        regionCityMap.put("Тернопільська область", "Ternopil");
        regionCityMap.put("Рівненська область", "Rivne");
        regionCityMap.put("Миколаївська область", "Mykolaiv");
        regionCityMap.put("Херсонська область", "Kherson");
        regionCityMap.put("Житомирська область", "Zhytomyr");
        regionCityMap.put("Кіровоградська область", "Kropyvnytskyi");
        regionCityMap.put("Сумська область", "Sumy");
        regionCityMap.put("Закарпатська область", "Uzhhorod");
        regionCityMap.put("Чернівецька область", "Chernivtsi");
        regionCityMap.put("Донецька область", "Donetsk");
        regionCityMap.put("Луганська область", "Luhansk");
        regionCityMap.put("АР Крим", "Simferopol");
    }

    private String mapRegionToCity(String region) {
        return regionCityMap.getOrDefault(region, "Kyiv");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameView = findViewById(R.id.tvDetailName);
        regionView = findViewById(R.id.tvDetailRegion);
        descriptionView = findViewById(R.id.tvDetailDescription);
        weatherView = findViewById(R.id.tvWeather);
        imageView = findViewById(R.id.ivDetail);
        //favoriteButton = findViewById(R.id.ivFavoriteButton);

        String placeId = getIntent().getStringExtra("place_id");
        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        //historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        if (placeId != null) {
            placeViewModel.getPlaceById(placeId).observe(this, place -> {
                if (place != null) {
                    bindPlace(place);
                    loadWeather(place.region);

                    //favoriteButton.setOnClickListener(...)
                    //historyViewModel.insert(...)

                } else {
                    Toast.makeText(this, "Місце не знайдено", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "Немає ID місця", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void bindPlace(Place place) {
        nameView.setText(place.name);
        regionView.setText(place.region);
        descriptionView.setText(place.description);
        Glide.with(this).load(place.imageUrl).into(imageView);
    }

    private String formatUnixTime(long timestamp) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        sdf.setTimeZone(java.util.TimeZone.getDefault());
        return sdf.format(new java.util.Date(timestamp * 1000L));
    }

    private void loadWeather(String region) {
        String city = mapRegionToCity(region);
        placeViewModel.loadWeather(city);
        placeViewModel.getWeatherLiveData().observe(this, weather -> {
            if (weather != null) {
                weatherView.setText(
                        " ☁️  Погода: " + weather.getDescription() + "\n" +
                                " 🌤  Температура: " + String.format("%.1f", weather.getTemp()) + "°C\n" +
                                " 🌡️  Відчувається як: " + String.format("%.1f", weather.getFeelsLike()) + "°C\n" +
                                " 🔽  Мін: " + String.format("%.1f", weather.getTempMin()) + "°C\n" +
                                " 🔼  Макс: " + String.format("%.1f", weather.getTempMax()) + "°C\n" +
                                " 💧  Вологість: " + weather.getHumidity() + "%\n" +
                                " 🔵  Тиск: " + weather.getPressure() + " гПа\n" +
                                " 🌬️  Вітер: " + weather.getWindSpeed() + " м/с\n" +
                                " 🌫  Хмарність: " + weather.getClouds() + "%\n" +
                                " 🌅  Схід сонця: " + formatUnixTime(weather.getSunrise()) + "\n" +
                                " 🌇  Захід сонця: " + formatUnixTime(weather.getSunset())
                );
            } else {
                weatherView.setText("Немає даних про погоду для " + city + ". Перевірте API ключ.");
            }
        });
    }

}
