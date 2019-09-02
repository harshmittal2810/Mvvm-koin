package com.harsh.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.harsh.common.base.BaseFragment
import com.harsh.common.base.BaseViewModel
import com.harsh.home.databinding.FragmentCategoryBinding
import com.harsh.home.views.CategoryAdapter
import com.harsh.model.CategoryData
import com.harsh.repository.utils.Resource
import com.harsh.repository.utils.Resource.Status.SUCCESS
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [BaseFragment] subclass
 * that will show the [CategoryData] list.
 */
class CategoryFragment : BaseFragment() {

  companion object {
    fun newInstance(url: String): CategoryFragment {
      Log.e(
        "Category",
        "Data $url"
      )
      val bundle = Bundle()
      bundle.putString("data", url)
      val categoryFragment = CategoryFragment()
      categoryFragment.arguments = bundle
      return categoryFragment
    }
  }

  // FOR DATA
  private val viewModel: CategoryViewModel by viewModel()
  private lateinit var binding: FragmentCategoryBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentCategoryBinding.inflate(inflater, container, false)
    binding.viewmodel = viewModel
    binding.lifecycleOwner = viewLifecycleOwner
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.loadDataWhenActivityStarts(arguments?.getString("data") ?: "")
    configureRecyclerView()
  }

  private fun configureRecyclerView() {
    binding.fragmentCategoryRv.adapter = CategoryAdapter(viewModel)
    viewModel.categoryData.observe(this, changeObserver)
  }

  override fun getViewModel(): BaseViewModel = viewModel

  private val changeObserver = Observer<Resource<List<CategoryData>>> { value ->
    if (value.status == SUCCESS) {
      val adapter = binding.fragmentCategoryRv.adapter as CategoryAdapter
      value.data?.let { adapter.updateData(it) }
    }
  }
}
