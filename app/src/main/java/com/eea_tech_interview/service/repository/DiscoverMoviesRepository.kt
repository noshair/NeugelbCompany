package com.eea_tech_interview.service.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.eea_tech_interview.section.discovermovie.ui.DiscoveryPagingSource
import com.eea_tech_interview.service.api_service.ApiService
import javax.inject.Inject

@ExperimentalPagingApi
class DiscoverMoviesRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getMovies() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),
        pagingSourceFactory = {
            DiscoveryPagingSource(apiService)
        }
    ).flow

}