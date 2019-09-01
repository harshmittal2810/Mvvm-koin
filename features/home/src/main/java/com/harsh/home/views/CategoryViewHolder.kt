package com.harsh.home.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harsh.home.CategoryViewModel
import com.harsh.home.databinding.ItemHomeCategoryBinding
import com.harsh.model.CategoryData

class CategoryViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

  private val binding = ItemHomeCategoryBinding.bind(parent)

  fun bindTo(categoryData: CategoryData, viewModel: CategoryViewModel) {
    binding.product = categoryData
    binding.viewmodel = viewModel
  }
}