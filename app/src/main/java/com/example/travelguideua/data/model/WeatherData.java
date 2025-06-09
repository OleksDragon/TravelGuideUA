package com.example.travelguideua.data.model;

public class WeatherData {
    private String description;
    private float temp;
    private float feelsLike;
    private float tempMin;
    private float tempMax;
    private int humidity;
    private int pressure;
    private float windSpeed;
    private int clouds;
    private long sunrise;
    private long sunset;


    public WeatherData(String description, float temp, float feelsLike, float tempMin, float tempMax,
                       int humidity, int pressure, float windSpeed, int clouds, long sunrise, long sunset) {
        this.description = description;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.clouds = clouds;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getDescription() { return description; }
    public float getTemp() { return temp; }
    public float getFeelsLike() { return feelsLike; }
    public float getTempMin() { return tempMin; }
    public float getTempMax() { return tempMax; }
    public int getHumidity() { return humidity; }
    public int getPressure() { return pressure; }
    public float getWindSpeed() { return windSpeed; }
    public int getClouds() { return clouds; }
    public long getSunrise() { return sunrise; }
    public long getSunset() { return sunset; }
}

