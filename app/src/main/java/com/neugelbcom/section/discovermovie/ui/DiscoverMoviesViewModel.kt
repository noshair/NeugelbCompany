package com.neugelbcom.section.discovermovie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.neugelbcom.service.repository.DiscoverMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(repository: DiscoverMoviesRepository) : ViewModel() {
    val list = repository.getQuotes().cachedIn(viewModelScope)
}