package com.yegor.seekingalpha.seekingalpha;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * model representing server response
 */
class PlanetsResponse {

    public int page;

    @SerializedName("items")
    List<Planet> planets;
}
