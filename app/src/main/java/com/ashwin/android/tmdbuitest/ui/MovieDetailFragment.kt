package com.ashwin.android.tmdbuitest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.data.model.Movie
import com.ashwin.android.tmdbuitest.data.source.MovieDataSource
import com.ashwin.android.tmdbuitest.databinding.FragmentMovieDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieDetailFragment(
    private val requestOptions: RequestOptions,
    private val movieDataSource: MovieDataSource
) : Fragment(R.layout.fragment_movie_detail) {
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.getInt("movie_id").let{ movieId ->
                movieDataSource.getMovie(movieId)?.let{ movieFromRemote ->
                    movie = movieFromRemote
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)

        setMovieDetails()

        binding.movieDirectorsButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_directors", movie.directors)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DirectorsFragment::class.java, bundle)
                ?.addToBackStack("DirectorsFragment")
                ?.commit()
        }

        binding.movieStarActorsButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_actors", movie.star_actors)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, StarActorsFragment::class.java, bundle)
                ?.addToBackStack("StarActorsFragment")
                ?.commit()
        }
    }

    private fun setMovieDetails(){
        movie.let{ nonNullMovie ->
            Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(nonNullMovie.image)
                .into(binding.movieImage)
            binding.movieTitle.text = nonNullMovie.title
            binding.movieDescription.text = nonNullMovie.description
        }
    }
}
