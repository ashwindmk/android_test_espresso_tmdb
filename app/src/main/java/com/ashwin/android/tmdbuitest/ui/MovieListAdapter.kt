package com.ashwin.android.tmdbuitest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashwin.android.tmdbuitest.data.model.Movie
import com.ashwin.android.tmdbuitest.databinding.ItemMovieBinding
import com.ashwin.android.tmdbuitest.util.EspressoIdlingResource
import com.bumptech.glide.Glide

class MovieListAdapter(
    private val interaction: Interaction? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    interface Interaction {
        fun onItemSelected(position: Int, item: Movie)
    }

    class MovieViewHolder(private val binding: ItemMovieBinding, private val interaction: Interaction?) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            binding.movieTitleTextView.text = item.title

            Glide.with(itemView)
                .load(item.image)
                .into(binding.movieImageView)

            item.star_actors?.let {
                for (index in 0 until it.size) {
                    var appendValue: String = it[index]
                    if (index < (it.size - 1)) {
                        appendValue += ", "
                    }
                    binding.movieStarActorsTextView.append(appendValue)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Movie>) {
        EspressoIdlingResource.increment()
        differ.submitList(list, Runnable {
            // RecyclerView update complete
            EspressoIdlingResource.decrement()
        })
    }
}
