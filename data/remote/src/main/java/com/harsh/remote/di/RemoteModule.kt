package com.harsh.remote.di

import com.harsh.remote.MercaiDatasource
import com.harsh.remote.MercaiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRemoteModule(baseUrl: String) = module {

  factory<Interceptor> {
    HttpLoggingInterceptor()
      .setLevel(HttpLoggingInterceptor.Level.HEADERS)
  }

  factory { OkHttpClient.Builder().addInterceptor(get()).build() }

  single {
    Retrofit.Builder()
      .client(get())
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()
  }

  factory { get<Retrofit>().create(MercaiService::class.java) }

  factory { MercaiDatasource(get()) }
}