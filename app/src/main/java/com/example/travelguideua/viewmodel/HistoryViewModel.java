package com.example.travelguideua.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelguideua.data.local.HistoryPlace;
import com.example.travelguideua.repository.HistoryRepository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private final HistoryRepository historyRepository;
    private final LiveData<List<HistoryPlace>> allHistory;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(application);
        allHistory = historyRepository.getAllHistory();
    }

    public LiveData<List<HistoryPlace>> getAllHistory() {
        return allHistory;
    }

    public void insert(HistoryPlace historyPlace) {
        historyRepository.insert(historyPlace);
    }

    public void delete(String placeId) {
        historyRepository.delete(placeId);
    }

    public void clearHistory() {
        historyRepository.clearHistory();
    }
}

