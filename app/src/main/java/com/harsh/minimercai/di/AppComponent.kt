package com.harsh.minimercai.di

import com.harsh.home.di.featureHomeModule
import com.harsh.local.di.localModule
import com.harsh.remote.di.createRemoteModule
import com.harsh.repository.di.repositoryModule

val appComponent = listOf(
  createRemoteModule("https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/"),
  repositoryModule,
  featureHomeModule,
  localModule
)