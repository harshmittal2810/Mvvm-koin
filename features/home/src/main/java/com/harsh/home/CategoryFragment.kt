package com.harsh.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harsh.common.base.BaseFragment
import com.harsh.common.base.BaseViewModel
import com.harsh.home.databinding.FragmentCategoryBinding
import com.harsh.home.views.CategoryAdapter
import com.harsh.model.Master
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [BaseFragment] subclass
 * that will show the [CategoryData] details.
 */
class CategoryFragment : BaseFragment() {

  companion object {
    lateinit var master: Master
    fun newInstance(master: Master): CategoryFragment {
      Log.e(
        "Category",
        "Data ${master.data} Name ${master.name}"
      )
      this.master = master
      return CategoryFragment()
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
    viewModel.loadDataWhenActivityStarts(master.data)
    configureRecyclerView()
  }

  private fun configureRecyclerView() {
    binding.fragmentCategoryRv.adapter = CategoryAdapter(viewModel)
  }
  override fun getViewModel(): BaseViewModel = viewModel

}
