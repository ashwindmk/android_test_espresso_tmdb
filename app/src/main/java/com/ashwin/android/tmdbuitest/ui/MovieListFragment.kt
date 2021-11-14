package com.ashwin.android.tmdbuitest.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.data.model.Movie
import com.ashwin.android.tmdbuitest.data.source.MovieDataSource
import com.ashwin.android.tmdbuitest.databinding.FragmentMovieListBinding
import com.ashwin.android.tmdbuitest.util.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.ClassCastException

class MovieListFragment(
    private val moviesDataSource: MovieDataSource
) : Fragment(R.layout.fragment_movie_list), MovieListAdapter.Interaction {
    private val TAG: String = MovieListFragment::class.java.simpleName

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    lateinit var uiCommunicationListener: UICommunicationListener
    lateinit var listAdapter: MovieListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            uiCommunicationListener = context as UICommunicationListener
        } catch (e: ClassCastException){
            Log.e(TAG, "Must implement interface in $activity: ${e.message}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieListBinding.bind(view)

        initRecyclerView()

        getData()
    }

    private fun initRecyclerView() {
        binding.movieListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            removeItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            listAdapter = MovieListAdapter(this@MovieListFragment)
            adapter = listAdapter
        }
    }

    private fun getData() {
        EspressoIdlingResource.increment()  // Letting Espresso know that there is a new bg task, wait for it to complete.
        uiCommunicationListener.loading(true)
        val job = GlobalScope.launch(Dispatchers.IO) {
            delay(2000L)
        }
        job.invokeOnCompletion {
            GlobalScope.launch(Dispatchers.Main) {
                uiCommunicationListener.loading(false)
                listAdapter.submitList(moviesDataSource.getMovies())
                EspressoIdlingResource.decrement()  // Letting Espresso know that bg task is complete, continue on UI thread.
            }
        }
    }

    override fun onItemSelected(position: Int, item: Movie) {
        activity?.run {
            val bundle = Bundle()
            bundle.putInt("movie_id", item.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .addToBackStack("MovieDetailFragment")
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
