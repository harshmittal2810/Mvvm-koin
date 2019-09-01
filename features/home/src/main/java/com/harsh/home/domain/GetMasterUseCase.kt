package com.harsh.home.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.harsh.model.Master
import com.harsh.repository.MercaiRepository
import com.harsh.repository.utils.Resource

/**
 * Use case that gets a [Resource][List][Master] from [MercaiRepository]
 * and makes some specific logic actions on it.
 */
class GetMasterUseCase(private val repository: MercaiRepository) {

  suspend operator fun invoke(forceRefresh: Boolean = false): LiveData<Resource<List<Master>>> {
    return Transformations.map(repository.getAllCategory(forceRefresh)) {
      it
    }
  }
}