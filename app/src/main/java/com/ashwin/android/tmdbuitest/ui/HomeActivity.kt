package com.ashwin.android.tmdbuitest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.data.source.MovieDataSource
import com.ashwin.android.tmdbuitest.data.source.RemoteMovieDataSource
import com.ashwin.android.tmdbuitest.databinding.ActivityHomeBinding
import com.ashwin.android.tmdbuitest.factory.MovieFragmentFactory
import com.bumptech.glide.request.RequestOptions

class HomeActivity : AppCompatActivity(), UICommunicationListener {
    private lateinit var binding: ActivityHomeBinding

    // dependencies (typically would be injected with dagger)
    lateinit var requestOptions: RequestOptions
    lateinit var movieDataSource: MovieDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        supportFragmentManager.fragmentFactory = MovieFragmentFactory(requestOptions, movieDataSource)

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun initDependencies() {
        // Glide
        requestOptions = RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)

        // Data Source
        movieDataSource = RemoteMovieDataSource
    }

    private fun init(){
//        if (supportFragmentManager.fragments.size == 0) {
//            val movieId = 1
//            val bundle = Bundle()
//            bundle.putInt("movie_id", movieId)
//            supportFragmentManager.beginTransaction()
//                .replace(binding.container.id, MovieDetailFragment::class.java, bundle)
//                .commit()
//        }
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment::class.java, null)
                .commit()
        }
    }

    override fun loading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
