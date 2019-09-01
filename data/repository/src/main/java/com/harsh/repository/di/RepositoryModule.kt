package com.harsh.repository.di

import com.harsh.repository.AppDispatchers
import com.harsh.repository.MercaiRepository
import com.harsh.repository.MercaiRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
  factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
  factory { MercaiRepositoryImpl(get(), get(), get()) as MercaiRepository }
}