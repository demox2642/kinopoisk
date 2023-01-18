package com.demox.kinopoisk.ui.detailscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.demox.kinopoisk.R
import com.demox.kinopoisk.databinding.FragmentDetailBinding
import com.demox.main.models.toUIString

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()
    private val viewBinding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie
        viewBinding.apply {
            ivPoster.load(movie.poster) { crossfade(true) }
            tvName.text = movie.name ?: movie.alternativeName
            tvDescription.text = movie.description ?: movie.shortDescription
            tvRaitingValue.text = movie.rating.toUIString()
            tvVotesValue.text = movie.votes.toUIString()
        }
    }
}
