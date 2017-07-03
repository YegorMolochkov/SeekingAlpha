package com.yegor.seekingalpha.seekingalpha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * model representing planet
 */
class Planet implements Serializable {

    String name = "No Name";

    @SerializedName("image_url")
    String URL;

    String description = "No Description";
}
