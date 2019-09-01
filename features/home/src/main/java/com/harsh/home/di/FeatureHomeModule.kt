package com.harsh.home.di

import com.harsh.home.CategoryViewModel
import com.harsh.home.HomeViewModel
import com.harsh.home.domain.GetCategoryDataUseCase
import com.harsh.home.domain.GetMasterUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
  factory { GetMasterUseCase(get()) }
  factory { GetCategoryDataUseCase(get()) }
  viewModel { HomeViewModel(get(), get()) }
  viewModel { CategoryViewModel(get(), get()) }
}