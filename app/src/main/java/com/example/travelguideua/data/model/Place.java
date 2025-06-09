package com.example.travelguideua.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "places")
public class Place {
    @PrimaryKey
    @NonNull
    public String id;
    public String name;
    public String region;
    public String description;
    public String imageUrl;
    public boolean isFavorite;

    public Place(@NonNull String id, String name, String region, String description, String imageUrl, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isFavorite = isFavorite;
    }

    // Гетери
    @NonNull public String getId() { return id; }
    public String getName() { return name; }
    public String getRegion() { return region; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public boolean isFavorite() { return isFavorite; }
}

