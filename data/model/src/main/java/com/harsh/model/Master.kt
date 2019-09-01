package com.harsh.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.concurrent.TimeUnit

@Entity
data class Master(

  @PrimaryKey
  @SerializedName("name")
  val name: String,

  @SerializedName("data")
  val data: String,

  var lastRefreshed: Date
) {
  /**
   * We consider that an [Master] is outdated when the last time
   * we fetched it was more than 30 minutes
   */
  fun haveToRefreshFromNetwork(): Boolean =
    TimeUnit.MILLISECONDS.toMinutes(Date().time - lastRefreshed.time) >= 30
}