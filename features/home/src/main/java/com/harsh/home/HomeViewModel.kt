package com.harsh.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.harsh.common.base.BaseViewModel
import com.harsh.common.utils.Event
import com.harsh.home.domain.GetMasterUseCase
import com.harsh.model.Master
import com.harsh.repository.AppDispatchers
import com.harsh.repository.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [HomeFragment].
 */
class HomeViewModel(
  private val getMasterUseCase: GetMasterUseCase,
  private val dispatchers: AppDispatchers
) : BaseViewModel() {

  // FOR DATA
  private val _master = MediatorLiveData<Resource<List<Master>>>()
  val mastersData: LiveData<Resource<List<Master>>> get() = _master
  private var usersSource: LiveData<Resource<List<Master>>> = MutableLiveData()
  private val _isLoading = MutableLiveData<Resource.Status>()
  val isLoading: LiveData<Resource.Status> get() = _isLoading

  init {
    getMasters(false)
  }

  fun userRefreshesItems() = getMasters(true)

  // ---

  private fun getMasters(forceRefresh: Boolean) = viewModelScope.launch(dispatchers.main) {
    _master.removeSource(usersSource) // We make sure there is only one source of livedata (allowing us properly refresh)
    withContext(dispatchers.io) { usersSource = getMasterUseCase(forceRefresh = forceRefresh) }
    _master.addSource(usersSource) {
      _master.value = it
      if (it.status == Resource.Status.ERROR) _snackbarError.value =
        Event(R.string.an_error_happened)
    }
  }
}