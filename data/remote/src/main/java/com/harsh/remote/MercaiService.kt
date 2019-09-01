package com.harsh.remote

import com.harsh.model.ApiResult
import com.harsh.model.CategoryData
import com.harsh.model.Master
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Url

interface MercaiService {

  @GET("master.json")
  fun fetchMasterJson(): Deferred<List<Master>>

  @GET
  fun fetchJson(@Url url: String): Deferred<List<CategoryData>>
}