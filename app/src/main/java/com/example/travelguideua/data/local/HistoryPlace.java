package com.example.travelguideua.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "history")
public class HistoryPlace {
    @PrimaryKey
    @NonNull
    public String placeId;
    public String name;
    public long timestamp;

    public HistoryPlace(@NonNull String placeId, String name, long timestamp) {
        this.placeId = placeId;
        this.name = name;
        this.timestamp = timestamp;
    }

    // Гетери та сетери
    @NonNull public String getPlaceId() { return placeId; }
    public void setPlaceId(@NonNull String placeId) { this.placeId = placeId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}

