package com.harsh.remote

/**
 * Implementation of [mercaiService] interface
 */
class MercaiDatasource(private val mercaiService: MercaiService) {

  fun fetchAllCategory() =
    mercaiService.fetchMasterJson()

  fun fetchCategoryJson(url: String) =
    mercaiService.fetchJson(url)
}