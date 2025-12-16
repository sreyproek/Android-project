package com.example.safetyapp;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HospitalResponse {
    @SerializedName("results")
    private List<Hospital> results;

    @SerializedName("status")
    private String status;

    public List<Hospital> getResults() { return results; }
    public void setResults(List<Hospital> results) { this.results = results; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}