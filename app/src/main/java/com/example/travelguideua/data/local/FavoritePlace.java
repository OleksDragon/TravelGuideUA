package com.example.travelguideua.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class FavoritePlace {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String description;
    public String city;
    public String imageUrl;

    public FavoritePlace(String name, String description, String city, String imageUrl) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.imageUrl = imageUrl;
    }

    // Гетери
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCity() { return city; }
    public String getImageUrl() { return imageUrl; }
}
