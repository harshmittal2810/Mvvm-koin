package com.harsh.home.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.harsh.model.CategoryData
import com.harsh.repository.MercaiRepository
import com.harsh.repository.utils.Resource

/**
 * Use case that gets a [Resource][List][CategoryData] from [MercaiRepository]
 * and makes some specific logic actions on it.
 */
class GetCategoryDataUseCase(private val repository: MercaiRepository) {

  suspend operator fun invoke(
    forceRefresh: Boolean = false,
    url: String
  ): LiveData<Resource<List<CategoryData>>> {
    return Transformations.map(repository.getCategoryData(forceRefresh, url)) {
      it
    }
  }
}