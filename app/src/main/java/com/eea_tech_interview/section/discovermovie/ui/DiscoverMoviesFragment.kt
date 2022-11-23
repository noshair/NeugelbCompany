package com.eea_tech_interview.section.discovermovie.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.ExperimentalPagingApi
import com.eea_tech_interview.R
import com.eea_tech_interview.section.discovermovie.ui.paggingadapter.DiscoveryPaggingAdapter
import com.eea_tech_interview.section.discovermovie.ui.paggingadapter.LoaderAdapter
import com.eea_tech_interview.service.extensions.Resource
import com.eea_tech_interview.service.model.discoverymodel.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_latest_movies.*


@ExperimentalPagingApi
@AndroidEntryPoint
class DiscoverMoviesFragment : Fragment(), DiscoveryPaggingAdapter.DiscoveryClickListener {
    lateinit var discoverMoviesViewModel: DiscoverMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        discoverMoviesViewModel = ViewModelProvider(this)[DiscoverMoviesViewModel::class.java]

        val adapter = DiscoveryPaggingAdapter(this)
        quoteList.setHasFixedSize(true)
        quoteList.adapter = adapter.withLoadStateHeaderAndFooter(LoaderAdapter(), LoaderAdapter())

        lifecycleScope.launchWhenCreated {

            discoverMoviesViewModel.getMovies()
            discoverMoviesViewModel.moviesList.collect {
                when (it) {
                    is Resource.OnFailure -> {
                        Log.d("Noshair", "Failure")
                    }
                    is Resource.OnSuccess -> {
                        Log.d("Noshairam", "Success")
                        it.data?.let { it1 ->
                            adapter.submitData(lifecycle, it1)
                        }
                    }
                    is Resource.OnLoading -> {
                        Log.d("Noshair", "Loading")
                    }
                }

            }
        }
    }

    override fun discoveryMovieClicked(item: Result?) {
        val bundle = bundleOf("detail" to item)
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(
            R.id.action_discoverMoviesFragment_to_detailMovieFragment,
            bundle
        )
    }
}