package com.harsh.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.concurrent.TimeUnit

@Entity
data class CategoryData(
  @PrimaryKey
  @SerializedName("id")
  val id: String,

  @SerializedName("name")
  val name: String?,

  @SerializedName("num_comments")
  val numComments: Int,

  @SerializedName("num_likes")
  val numLikes: Int,

  @SerializedName("photo")
  val photo: String?,

  @SerializedName("price")
  val price: Int?,

  @SerializedName("status")
  val status: String?,

  var lastRefreshed: Date
) {
  /**
   * We consider that an [CategoryData] is outdated when the last time
   * we fetched it was more than 10 minutes
   */
  fun haveToRefreshFromNetwork(): Boolean =
    TimeUnit.MILLISECONDS.toMinutes(Date().time - lastRefreshed.time) >= 10

  fun getTitle() = name.toString().toTitleCase()
  fun getPriceVal(): String = price.toString().toNumCase()
  fun getFavCount(): String = numLikes.toString().toNumCase()
  fun getCommentCount(): String = numComments.toString().toNumCase()
}