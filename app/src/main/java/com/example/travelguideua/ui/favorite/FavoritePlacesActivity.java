package com.example.travelguideua.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelguideua.R;
import com.example.travelguideua.data.local.FavoritePlace;
import com.example.travelguideua.ui.detail.DetailActivity;
import com.example.travelguideua.viewmodel.FavoriteViewModel;

public class FavoritePlacesActivity extends AppCompatActivity implements FavoriteAdapter.OnItemClickListener {

    private FavoriteViewModel favoriteViewModel;
    private FavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_places);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavoriteAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        favoriteViewModel.getAllFavorites().observe(this, favoritePlaces -> {
            adapter.setFavoritePlaces(favoritePlaces);
        });
    }

    @Override
    public void onItemClick(FavoritePlace favoritePlace) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("place_id", favoritePlace.getName());
        Toast.makeText(this, "Відкриття деталей: " + favoritePlace.getName(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(FavoritePlace favoritePlace) {
        favoriteViewModel.delete(favoritePlace);
        Toast.makeText(this, "Місце видалено з обраних: " + favoritePlace.getName(), Toast.LENGTH_SHORT).show();
    }
}
