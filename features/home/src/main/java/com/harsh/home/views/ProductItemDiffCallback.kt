package com.harsh.home.views

import androidx.recyclerview.widget.DiffUtil
import com.harsh.model.CategoryData

class ProductItemDiffCallback(
  private val oldList: List<CategoryData>,
  private val newList: List<CategoryData>
) : DiffUtil.Callback() {

  override fun getOldListSize() = oldList.size

  override fun getNewListSize() = newList.size

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
    oldList[oldItemPosition] == newList[newItemPosition]

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].id == newList[newItemPosition].id
        && oldList[oldItemPosition].name == newList[newItemPosition].name
        && oldList[oldItemPosition].photo == newList[newItemPosition].photo
  }
}