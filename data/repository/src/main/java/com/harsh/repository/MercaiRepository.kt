package com.harsh.repository

import androidx.lifecycle.LiveData
import com.harsh.local.dao.CategoryDao
import com.harsh.local.dao.MercaiDao
import com.harsh.model.CategoryData
import com.harsh.model.Master
import com.harsh.remote.MercaiDatasource
import com.harsh.repository.utils.NetworkBoundResource
import com.harsh.repository.utils.Resource
import kotlinx.coroutines.Deferred

interface MercaiRepository {
  suspend fun getAllCategory(forceRefresh: Boolean = false): LiveData<Resource<List<Master>>>
  suspend fun getCategoryData(
    forceRefresh: Boolean = false,
    url: String
  ): LiveData<Resource<List<CategoryData>>>
}

class MercaiRepositoryImpl(
  private val datasource: MercaiDatasource,
  private val mercaiDao: MercaiDao,
  private val categoryDao: CategoryDao
) : MercaiRepository {

  override suspend fun getAllCategory(forceRefresh: Boolean): LiveData<Resource<List<Master>>> {
    return object : NetworkBoundResource<List<Master>, List<Master>>() {

      override fun processResponse(response: List<Master>): List<Master> = response

      override suspend fun saveCallResults(items: List<Master>) = mercaiDao.save(items)

      override fun shouldFetch(data: List<Master>?): Boolean =
        data == null || data.isEmpty() || forceRefresh

      override suspend fun loadFromDb(): List<Master> = mercaiDao.getMasterList()

      override fun createCallAsync(): Deferred<List<Master>> = datasource.fetchAllCategory()

    }.build().asLiveData()
  }

  override suspend fun getCategoryData(
    forceRefresh: Boolean,
    url: String
  ): LiveData<Resource<List<CategoryData>>> {
    return object : NetworkBoundResource<List<CategoryData>, List<CategoryData>>() {

      override fun processResponse(response: List<CategoryData>): List<CategoryData> =
        response

      override suspend fun saveCallResults(items: List<CategoryData>) {
        items.forEach {
          it.apply {
            it.categoryName = url
          }
        }
        categoryDao.save(items)
      }

      override fun shouldFetch(data: List<CategoryData>?): Boolean =
        data == null || data.isEmpty() || forceRefresh

      override suspend fun loadFromDb(): List<CategoryData> = categoryDao.getCategoryList(url)

      override fun createCallAsync(): Deferred<List<CategoryData>> =
        datasource.fetchCategoryJson(url)

    }.build().asLiveData()
  }
}