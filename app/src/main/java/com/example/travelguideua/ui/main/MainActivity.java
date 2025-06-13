package com.example.travelguideua.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelguideua.R;
import com.example.travelguideua.data.model.Place;
import com.example.travelguideua.ui.detail.DetailActivity;
import com.example.travelguideua.ui.favorite.FavoritePlacesActivity;
import com.example.travelguideua.ui.history.HistoryActivity;
import com.example.travelguideua.viewmodel.PlaceViewModel;


public class MainActivity extends AppCompatActivity {
    private PlaceViewModel placeViewModel;
    private PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlaceAdapter(this::openDetail);
        recyclerView.setAdapter(adapter);

        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

        placeViewModel.getAllPlaces().observe(this, adapter::setPlaces);

        // Додаємо тестові дані
        if (getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true)) {
            addSampleData();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("firstrun", false).apply();
            Log.d("MainActivity", "Додано тестові дані при першому запуску.");
        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // placeViewModel.search(query).observe(MainActivity.this, adapter::setPlaces); // Оновлюємо, щоб не створювати новий LiveData
                placeViewModel.search(query).removeObservers(MainActivity.this); // Важливо: видаляємо попередні обсервери
                placeViewModel.search(query).observe(MainActivity.this, adapter::setPlaces);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // placeViewModel.search(newText).observe(MainActivity.this, adapter::setPlaces); // Оновлюємо, щоб не створювати новий LiveData
                placeViewModel.search(newText).removeObservers(MainActivity.this); // Важливо: видаляємо попередні обсервери
                placeViewModel.search(newText).observe(MainActivity.this, adapter::setPlaces);
                return true;
            }
        });
    }

    // Метод для відкриття детальної активності
    private void openDetail(Place place) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("place_id", place.id);
        startActivity(intent);

        Log.d("MainActivity", "Натиснуто на місце: " + place.getName());
    }

    // Метод для додавання тестових даних
    private void addSampleData() {
        placeViewModel.insert(new Place("1", "Київ", "Київська область", "Столиця України, відома Золотими воротами та Софійським собором.", "https://etnotur.ua/wp-content/uploads/2016/04/Kiev.jpg", false));
        placeViewModel.insert(new Place("2", "Львів", "Львівська область", "Культурна столиця України з унікальною архітектурою та атмосферою.", "https://cfts.org.ua/imglib/f02513eece724b732a75df30bb96e495.jpg", false));
        placeViewModel.insert(new Place("3", "Одеса", "Одеська область", "Приморське місто, відоме своїми пляжами та Оперним театром.", "https://chornemore.com/wp-content/uploads/2023/03/odessa-sights-9.jpg", false));
        placeViewModel.insert(new Place("4", "Харків", "Харківська область", "Друге за величиною місто України, промисловий та науковий центр.", "https://beket.com.ua/wp-content/uploads/_k/kharkov-06.jpg", false));
        placeViewModel.insert(new Place("5", "Дніпро", "Дніпропетровська область", "Велике індустріальне місто на березі Дніпра.", "https://upload.wikimedia.org/wikipedia/commons/7/77/Dnipropetrovsk_view_2015_tov-tob.jpg", false));
    }

    // Метод для створення меню опцій
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Обробка вибору пункту меню
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorites) {
            startActivity(new Intent(this, FavoritePlacesActivity.class));
            return true;
        }
         else if (id == R.id.action_history) {
             startActivity(new Intent(this, HistoryActivity.class));
             return true;
         }
        return super.onOptionsItemSelected(item);
    }
}