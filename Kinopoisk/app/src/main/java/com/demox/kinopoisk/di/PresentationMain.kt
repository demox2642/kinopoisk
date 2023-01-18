package com.demox.kinopoisk.di

import com.demox.kinopoisk.ui.mainscreen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationMain = module {
    viewModel { MainViewModel(get()) }
}
