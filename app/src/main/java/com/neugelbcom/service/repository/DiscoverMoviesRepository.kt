package com.neugelbcom.service.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.neugelbcom.section.discovermovie.ui.DiscoveryPagingSource
import com.neugelbcom.service.api_service.ApiService
import com.neugelbcom.service.baseurl.ApiConstants
import javax.inject.Inject

@ExperimentalPagingApi
class DiscoverMoviesRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getQuotes() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),

        pagingSourceFactory = {
            DiscoveryPagingSource(apiService)
        }
    ).liveData

}