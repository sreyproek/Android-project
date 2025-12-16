package com.example.safetyapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // Endpoint for user login
    @POST("api/users/login") // Example URL: http://your-server.com/api/users/login
    Call<User> loginUser(@Body User user);

    // Endpoint for user registration
    @POST("api/users/register") // Example URL: http://your-server.com/api/users/register
    Call<User> registerUser(@Body User user);

    // You could also add an endpoint for SOS alerts
    @POST("api/alerts/sos")
    Call<Void> sendSosAlert(@Body SosAlert alert); // Example for later
}

// You will need these simple data classes too.
// You can put them in the same file for simplicity or in their own files.

class User {
    private String name;
    private String email;
    private String password;

    // Constructors, Getters, and Setters
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and setters are needed for Gson to work
    public String getName() { return name; }
    public String getEmail() { return email; }
}

class SosAlert {
    private String userId;
    private double latitude;
    private double longitude;

    // Constructors, getters, setters
}
