package com.harsh.minimercai

import android.app.Application
import com.harsh.minimercai.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.DEBUG

open class MiniMercaiApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    configureDi()
  }

  // CONFIGURATION ---
  open fun configureDi() =
    startKoin {
      androidLogger(DEBUG)
      androidContext(this@MiniMercaiApplication)
      modules(appComponent)
    }
}