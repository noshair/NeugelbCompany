package com.eea_tech_interview.section.discovermovie.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eea_tech_interview.service.extensions.Resource
import com.eea_tech_interview.service.repository.DiscoverMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.eea_tech_interview.service.model.discoverymodel.Result
@ExperimentalPagingApi
@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(private val repository: DiscoverMoviesRepository) : ViewModel() {
    private val _moviesList = MutableStateFlow<Resource<PagingData<Result>>>(Resource.OnLoading())
    val moviesList : StateFlow<Resource<PagingData<Result>>> get()=_moviesList
    fun getMovies(){
        viewModelScope.launch{
            repository.getMovies().cachedIn(viewModelScope).catch {
                _moviesList.value = Resource.OnFailure()
            }.collect {
                Log.d("Nosh air",it.toString())
                _moviesList.value = Resource.OnSuccess(it)
            }
        }

    }}