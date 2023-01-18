package com.demox.kinopoisk.ui.mainscreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.demox.kinopoisk.R
import com.demox.kinopoisk.databinding.MovieListItemBinding
import com.demox.main.models.Movie

class MoviesAdapter : PagingDataAdapter<Movie, MoviesAdapter.ViewHolder>(MoviesDiiUtil()) {

    var onItemClick: (Movie) -> Unit = { _ -> }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding by viewBinding(MovieListItemBinding::bind)

        fun bind(movie: Movie, onItemClick: (Movie) -> Unit) {
            viewBinding.apply {
                ivPoster.load(movie.poster) { crossfade(true) }
                tvName.text = movie.name
                tvId.text = movie.id.toString()
                tvYear.text = movie.year.toString()
                root.setOnClickListener { onItemClick(movie) }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, onItemClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        )
    }
}
