package com.eea_tech_interview.service.api_service

import com.eea_tech_interview.service.model.discoverymodel.DiscoveryModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/discover/movie?")
    suspend fun discoverMovies(@Query("api_key") apikey: String,
                               @Query("page") page: Int): DiscoveryModel
}