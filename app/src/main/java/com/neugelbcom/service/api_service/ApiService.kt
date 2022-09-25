package com.neugelbcom.service.api_service

import com.neugelbcom.service.model.discoverymodel.DiscoveryModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/discover/movie?")
    suspend fun discovermovies(@Query("api_key") apikey: String,
                               @Query("page") page: Int): DiscoveryModel
}