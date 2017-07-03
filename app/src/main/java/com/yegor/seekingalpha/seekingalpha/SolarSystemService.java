package com.yegor.seekingalpha.seekingalpha;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Server API
 */
interface SolarSystemService {

    @GET
    Call<PlanetsResponse> loadPlanetsInfo(@Url String pageNum);
}
