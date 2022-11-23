package com.eea_tech_interview.section.discovermovie.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eea_tech_interview.service.api_service.ApiService
import com.eea_tech_interview.service.baseurl.ApiConstants
import com.eea_tech_interview.service.model.discoverymodel.Result
import java.lang.Exception

    const val STARTING_PAGE_INDEX = 1

    class DiscoveryPagingSource(private val apiService: ApiService) : PagingSource<Int, Result>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
            return try {
                val position = params.key ?: STARTING_PAGE_INDEX
                val response = apiService.discoverMovies(ApiConstants.API_KEY,position)

                return LoadResult.Page(
                    data = response.results,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (position == response.total_pages) null else position + 1
                )

            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }

    }
