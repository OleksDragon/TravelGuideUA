package com.example.travelguideua.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelguideua.R;
import com.example.travelguideua.data.local.HistoryPlace;
import com.example.travelguideua.ui.detail.DetailActivity;
import com.example.travelguideua.viewmodel.HistoryViewModel;

public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.OnItemClickListener {

    private HistoryViewModel historyViewModel;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyViewModel.getAllHistory().observe(this, historyPlaces -> {
            adapter.setHistoryPlaces(historyPlaces);
        });

        Button clearHistoryButton = findViewById(R.id.btnClearHistory);
        clearHistoryButton.setOnClickListener(v -> {
            historyViewModel.clearHistory();
            Toast.makeText(this, "Історія очищена", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onItemClick(HistoryPlace historyPlace) {
        // Обробка натискання на елемент історії: відкрити деталі місця
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("place_id", historyPlace.getPlaceId()); // Передача ID місця
        Toast.makeText(this, "Відкриття з історії: " + historyPlace.getName(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}

