package com.harsh.repository.utils

import com.harsh.repository.utils.Resource.Status.ERROR
import com.harsh.repository.utils.Resource.Status.LOADING
import com.harsh.repository.utils.Resource.Status.SUCCESS

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
  companion object {
    fun <T> success(data: T?): Resource<T> {
      return Resource(
        SUCCESS,
        data,
        null
      )
    }

    fun <T> error(error: Throwable, data: T?): Resource<T> {
      return Resource(
        ERROR,
        data,
        error
      )
    }

    fun <T> loading(data: T?): Resource<T> {
      return Resource(
        LOADING,
        data,
        null
      )
    }
  }

  enum class Status {
    SUCCESS,
    ERROR,
    LOADING
  }
}