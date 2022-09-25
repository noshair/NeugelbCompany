package com.neugelbcom.section.discovermovie.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.RecyclerView
import com.neugelbcom.R
import com.neugelbcom.section.discovermovie.ui.paggingadapter.DiscoveryPaggingAdapter
import com.neugelbcom.section.discovermovie.ui.paggingadapter.LoaderAdapter
import com.neugelbcom.service.model.discoverymodel.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_latest_movies.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LatestMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalPagingApi
@AndroidEntryPoint
class DiscoverMoviesFragment : Fragment(), DiscoveryPaggingAdapter.DiscoveryClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var discoverMoviesViewModel: DiscoverMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        discoverMoviesViewModel = ViewModelProvider(this).get(DiscoverMoviesViewModel::class.java)

        val adapter = DiscoveryPaggingAdapter(this)
        quoteList.setHasFixedSize(true)
        quoteList.adapter = adapter.withLoadStateHeaderAndFooter(LoaderAdapter(), LoaderAdapter())

        discoverMoviesViewModel.list.observe(requireActivity()) {
            adapter.submitData(lifecycle, it)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LatestMoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiscoverMoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
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