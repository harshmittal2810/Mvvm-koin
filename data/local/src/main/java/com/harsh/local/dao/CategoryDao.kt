package com.harsh.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.harsh.model.CategoryData
import java.util.Date

@Dao
abstract class CategoryDao : BaseDao<CategoryData>() {

  @Query("SELECT * FROM CategoryData WHERE categoryName = :categoryName ORDER BY name")
  abstract suspend fun getCategoryList(categoryName: String): List<CategoryData>

  @Query("SELECT * FROM CategoryData WHERE name = :name")
  abstract suspend fun getCategoryData(name: String): CategoryData

  // ---

  /**
   * Each time we save an categoryData, we update its 'lastRefreshed' field
   * This allows us to know when we have to refresh its data
   */

  suspend fun save(categoryData: CategoryData) {
    insert(categoryData.apply { lastRefreshed = Date() })
  }

  suspend fun save(categoryDatas: List<CategoryData>) {
    insert(categoryDatas.apply { forEach { it.lastRefreshed = Date() } })
  }
}