package com.harsh.home.views

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.harsh.model.CategoryData
import com.harsh.model.Master
import com.harsh.repository.utils.Resource

object BindingAdapter {

  @BindingAdapter("app:showWhenLoading")
  @JvmStatic
  fun <T> showWhenLoading(view: SwipeRefreshLayout, resource: Resource<T>?) {
    Log.d(BindingAdapter::class.java.simpleName, "Resource: $resource")
    if (resource != null) view.isRefreshing = resource.status == Resource.Status.LOADING
  }

  @BindingAdapter("app:showWhenLoading")
  @JvmStatic
  fun showWhenLoading(view: SwipeRefreshLayout, status: Resource.Status?) {
    Log.d(BindingAdapter::class.java.simpleName, "Status: $status")
    status?.let {
      view.isRefreshing = it == Resource.Status.LOADING
    }
  }

  @BindingAdapter("app:imageUrl")
  @JvmStatic
  fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
      .load(url)
      .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
      .transition(withCrossFade())
      .into(view)
  }

  @BindingAdapter("app:items")
  @JvmStatic
  fun setItems(recyclerView: RecyclerView, resource: Resource<List<CategoryData>>?) {
    with(recyclerView.adapter as CategoryAdapter) {
      resource?.data?.let { updateData(it) }
    }
  }

  @BindingAdapter("app:pagerItems")
  @JvmStatic
  fun setPagerItems(viewPager: ViewPager, resource: Resource<List<Master>>?) {
    with(viewPager.adapter as FragmentSectionsAdapter) {
      resource?.data?.let { prepareTabs(it.toMutableList()) }
    }
  }

  @BindingAdapter("app:showWhenEmptyList")
  @JvmStatic
  fun <T> showMessageErrorWhenEmptyList(view: View, resource: Resource<List<T>>?) {
    if (resource != null) {
      view.visibility = if (resource.status == Resource.Status.ERROR
        && resource.data != null
        && resource.data!!.isEmpty()
      ) View.VISIBLE else View.GONE
    }
  }

  @BindingAdapter("app:showWhenSoldOut")
  @JvmStatic
  fun showWhenSoldOut(view: View, status: String) {
    view.visibility = if (status == "sold_out"
    ) View.VISIBLE else View.GONE
  }

}