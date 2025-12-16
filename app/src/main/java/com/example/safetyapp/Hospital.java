package com.example.safetyapp;

import com.google.gson.annotations.SerializedName;

public class Hospital {
    @SerializedName("name")
    private String name;

    @SerializedName("vicinity")
    private String address;

    @SerializedName("geometry")
    private Geometry geometry;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Geometry getGeometry() { return geometry; }
    public void setGeometry(Geometry geometry) { this.geometry = geometry; }

    // Inner class for geometry
    public class Geometry {
        @SerializedName("location")
        private Location location;

        public Location getLocation() { return location; }
        public void setLocation(Location location) { this.location = location; }
    }

    public class Location {
        @SerializedName("lat")
        private double lat;

        @SerializedName("lng")
        private double lng;

        public double getLat() { return lat; }
        public void setLat(double lat) { this.lat = lat; }

        public double getLng() { return lng; }
        public void setLng(double lng) { this.lng = lng; }
    }
}