package com.harsh.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harsh.local.converter.Converters
import com.harsh.local.dao.CategoryDao
import com.harsh.local.dao.MercaiDao
import com.harsh.model.CategoryData
import com.harsh.model.Master

@Database(
  entities = [Master::class, CategoryData::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MercaiAppDatabase : RoomDatabase() {

  // DAO
  abstract fun mercaiDao(): MercaiDao

  abstract fun categoryDao(): CategoryDao

  companion object {

    fun buildDatabase(context: Context) =
      Room.databaseBuilder(
        context.applicationContext,
        MercaiAppDatabase::class.java,
        "MiniMercai.db"
      )
        .build()
  }
}