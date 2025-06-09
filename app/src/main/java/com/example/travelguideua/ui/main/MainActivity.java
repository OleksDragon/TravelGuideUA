package com.example.travelguideua.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelguideua.R;
import com.example.travelguideua.data.model.Place;
import com.example.travelguideua.ui.detail.DetailActivity;
import com.example.travelguideua.viewmodel.PlaceViewModel;


public class MainActivity extends AppCompatActivity {
    private PlaceViewModel placeViewModel;
    private PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Передача лямбда-виразу для обробки натискань на елементи списку
        adapter = new PlaceAdapter(this::openDetail);
        recyclerView.setAdapter(adapter);

        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

        // Спостерігаємо за списком місць з ViewModel і оновлюємо адаптер
        placeViewModel.getAllPlaces().observe(this, adapter::setPlaces);

        // Додаємо тестові дані.
        placeViewModel.getAllPlaces().observe(this, places -> {
            if (places == null || places.isEmpty()) {
                addSampleData();
                Log.d("MainActivity", "Додано тестові дані.");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                placeViewModel.search(query).observe(MainActivity.this, adapter::setPlaces);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
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

    // Поки що не реалізовано меню, це буде на пізніших кроках
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

