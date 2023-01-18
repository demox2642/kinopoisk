package com.demox.kinopoisk.ui.mainscreen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.demox.main.models.Movie

class MoviesDiiUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
