package com.harsh.home.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.harsh.home.CategoryViewModel
import com.harsh.home.R
import com.harsh.model.CategoryData

class CategoryAdapter(private val viewModel: CategoryViewModel) :
  RecyclerView.Adapter<CategoryViewHolder>() {

  private val categoryDataList: MutableList<CategoryData> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    CategoryViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.item_home_category,
        parent,
        false
      )
    )

  override fun getItemCount(): Int = categoryDataList.size

  override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
    holder.bindTo(categoryDataList[position], viewModel)

  // ---

  fun updateData(items: List<CategoryData>) {
    val diffCallback = ProductItemDiffCallback(categoryDataList, items)
    val diffResult = DiffUtil.calculateDiff(diffCallback)
    categoryDataList.clear()
    categoryDataList.addAll(items)
    diffResult.dispatchUpdatesTo(this)
  }

}