package com.harsh.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.harsh.common.base.BaseViewModel
import com.harsh.common.utils.Event
import com.harsh.home.domain.GetCategoryDataUseCase
import com.harsh.model.CategoryData
import com.harsh.repository.AppDispatchers
import com.harsh.repository.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [CategoryFragment].
 */
class CategoryViewModel(
  private val getCategoryDataUseCase: GetCategoryDataUseCase,
  private val dispatchers: AppDispatchers
) : BaseViewModel() {

  private lateinit var argsUrl: String

  private val _category = MediatorLiveData<Resource<List<CategoryData>>>()
  val categoryData: LiveData<Resource<List<CategoryData>>> get() = _category
  private var userSource: LiveData<Resource<List<CategoryData>>> = MutableLiveData()

  private val _isLoading = MutableLiveData<Resource.Status>()
  val isLoading: LiveData<Resource.Status> get() = _isLoading

  fun loadDataWhenActivityStarts(url: String) {
    argsUrl = url
    getCategoryData(false, argsUrl)
  }

  private fun getCategoryData(forceRefresh: Boolean, url: String) =
    viewModelScope.launch(dispatchers.main) {
      _category.removeSource(userSource)
      withContext(dispatchers.io) {
        userSource = getCategoryDataUseCase(forceRefresh = forceRefresh, url = url)
      }
      _category.addSource(userSource) {
        _category.value = it
        _isLoading.value = it.status
        if (it.status == Resource.Status.ERROR) _snackbarError.value =
          Event(R.string.an_error_happened)
      }
    }
}