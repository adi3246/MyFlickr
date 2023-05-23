package com.isa.myflickr.android.di

import com.isa.myflickr.android.ui.detail.DetailViewModel
import com.isa.myflickr.android.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Isa Andi on 19/05/2023.
 */
val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params -> DetailViewModel(post = params.get()) }
}