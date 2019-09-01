package com.harsh.local.di

import com.harsh.local.MercaiAppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
  single { MercaiAppDatabase.buildDatabase(androidContext()) }
  factory { (get() as MercaiAppDatabase).mercaiDao() }
  factory { (get() as MercaiAppDatabase).categoryDao() }
}