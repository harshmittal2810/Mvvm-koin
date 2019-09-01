package com.harsh.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.harsh.model.Master
import java.util.Date

@Dao
abstract class MercaiDao : BaseDao<Master>() {

  @Query("SELECT * FROM Master ")
  abstract suspend fun getMasterList(): List<Master>

  @Query("SELECT * FROM Master WHERE data = :url")
  abstract suspend fun getMaster(url: String): Master

  // ---

  /**
   * Each time we save an master data, we update its 'lastRefreshed' field
   * This allows us to know when we have to refresh its data
   */

  suspend fun save(master: Master) {
    insert(master.apply { lastRefreshed = Date() })
  }

  suspend fun save(masters: List<Master>) {
    insert(masters.apply { forEach { it.lastRefreshed = Date() } })
  }
}