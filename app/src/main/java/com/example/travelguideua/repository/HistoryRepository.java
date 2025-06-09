package com.example.travelguideua.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;

import com.example.travelguideua.data.db.AppDatabase;
import com.example.travelguideua.data.db.HistoryDao;
import com.example.travelguideua.data.local.HistoryPlace;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HistoryRepository {
    private HistoryDao historyDao;
    private LiveData<List<HistoryPlace>> allHistory;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public HistoryRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        historyDao = db.historyDao();
        allHistory = historyDao.getAllHistory();
    }

    public LiveData<List<HistoryPlace>> getAllHistory() {
        return allHistory;
    }

    public void insert(HistoryPlace historyPlace) {
        executor.execute(() -> historyDao.insert(historyPlace));
    }

    public void delete(String placeId) {
        executor.execute(() -> historyDao.deleteHistoryPlace(placeId));
    }

    public void clearHistory() {
        executor.execute(() -> historyDao.clearHistory());
    }
}
