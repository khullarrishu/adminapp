package com.example.surinderpalsinghsidhu.flagrunadmin;

public class Locations {


    public Double latitude;
    public Double longitude;
    public String Name;


    public Locations() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Locations(Double latitude, Double longitude,String playerName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.Name = playerName;

    }
}
