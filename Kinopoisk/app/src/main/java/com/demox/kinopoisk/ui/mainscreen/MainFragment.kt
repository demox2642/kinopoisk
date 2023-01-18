package com.demox.kinopoisk.ui.mainscreen

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.demox.kinopoisk.R
import com.demox.kinopoisk.databinding.FragmentMainBinding
import com.demox.kinopoisk.ui.mainscreen.adapter.MoviesAdapter
import com.demox.main.models.MovieType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by viewModel<MainViewModel>()
    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // отслеживаем изменения UI
        viewBinding.apply {
            flabFilter.setOnClickListener {
                viewModel.changeFilterVisible()
            }
            // устанавливаем адаптер
            rvMovieList.adapter = MoviesAdapter().apply {
                onItemClick = {
                    val bundle = Bundle()
                    bundle.putParcelable("movie", it)
                    findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
                }
            }
            rvMovieList.layoutManager = LinearLayoutManager(requireContext())
            etSearch.doAfterTextChanged {
                viewModel.changeSearchText(it?.toString() ?: "")
            }
            etYear.doAfterTextChanged {
                viewModel.changeYearText(it?.toString() ?: "")
            }

            rgFilter.setOnCheckedChangeListener { group, checkedId ->
                viewModel.changeMovieType(checkedId.idToMovieType())
            }
        }
        bindViewModel()
        // отслеживаем изменения в фильтрах и отправляем запрос на сервер
        lifecycleScope.launchWhenResumed {
            combine(
                viewModel.searchQuery.debounce(500L),
                viewModel.year.debounce(500L),
                viewModel.movieType
            ) { query, year, type ->
                viewModel.search(query, year, type)
            }.collect()
        }
    }

    private fun bindViewModel() {
        // следим за изменениями в данных и отправляем их в UI
        // фильтры
        lifecycleScope.launchWhenCreated {
            viewModel.showFilter.collectLatest {
                if (it) {
                    viewBinding.flabFilter.imageTintList = ColorStateList.valueOf(Color.GREEN)
                    viewBinding.rgFilter.visibility = View.VISIBLE
                    viewBinding.llYear.visibility = View.VISIBLE
                } else {
                    viewBinding.flabFilter.imageTintList = ColorStateList.valueOf(Color.WHITE)
                    viewBinding.rgFilter.visibility = View.GONE
                    viewBinding.llYear.visibility = View.GONE
                }
            }
        }
        // список фильмов
        lifecycleScope.launchWhenCreated {
            viewModel.movieList.collectLatest {
                (viewBinding.rvMovieList.adapter as MoviesAdapter)
                    .submitData(it)
            }
        }
    }

    // переводим id radio button в MovieType
    private fun Int.idToMovieType(): MovieType {
        return when (this) {
            R.id.rb_all -> MovieType.ALL
            R.id.rb_movie -> MovieType.MOVIE
            R.id.rb_cartoon -> MovieType.CARTOON
            R.id.rb_series -> MovieType.SERIES
            else -> MovieType.ALL
        }
    }
}
