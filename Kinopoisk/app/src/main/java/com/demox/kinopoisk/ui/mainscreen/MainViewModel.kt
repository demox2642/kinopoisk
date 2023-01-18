package com.demox.kinopoisk.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.demox.main.models.Movie
import com.demox.main.models.MovieType
import com.demox.main.models.toServerCall
import com.demox.main.usecase.GetMovieListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {
    private val _showFilter = MutableStateFlow(false)
    val showFilter = _showFilter.asStateFlow()
    private val _movieList = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movieList = _movieList.asStateFlow()
    private val _movieType = MutableStateFlow(MovieType.ALL)
    val movieType = _movieType.asStateFlow()
    private val _year = MutableStateFlow("")
    val year = _year.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
// загружаем список фильмов при инициализации
        viewModelScope.launch {
            getMovieListUseCase.getMovieList("", "", _movieType.value.toServerCall())
                .cachedIn(viewModelScope)
                .collectLatest {
                    _movieList.value = it
                }
        }
    }

    fun changeFilterVisible() {
        _showFilter.value = _showFilter.value.not()
    }

    fun changeSearchText(search: String) {
        _searchQuery.value = search
    }

    fun changeYearText(search: String) {
        _year.value = search
    }

    fun changeMovieType(movieType: MovieType) {
        _movieType.value = movieType
    }

    suspend fun search(query: String, year: String, type: MovieType) {
        viewModelScope.launch {
            getMovieListUseCase.getMovieList(query, year, type.toServerCall())
                .cachedIn(viewModelScope)
                .collectLatest {
                    _movieList.value = it
                }
        }
    }
}
