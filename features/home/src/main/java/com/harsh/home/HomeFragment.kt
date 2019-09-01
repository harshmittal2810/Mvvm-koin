package com.harsh.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harsh.common.base.BaseFragment
import com.harsh.common.base.BaseViewModel
import com.harsh.home.databinding.FragmentHomeBinding
import com.harsh.home.views.FragmentSectionsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [BaseFragment] subclass
 * that will show a list of top [User] from Github's API.
 */
class HomeFragment : BaseFragment() {

  // FOR DATA
  private val viewModel: HomeViewModel by viewModel()
  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding.viewmodel = viewModel
    binding.lifecycleOwner = viewLifecycleOwner
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    configureViewPager()
  }

  private fun configureViewPager() {
    binding.pager.adapter = FragmentSectionsAdapter(childFragmentManager)
    binding.tabLayout.setupWithViewPager(binding.pager, true)
  }

  override fun getViewModel(): BaseViewModel = viewModel
  // ---

}
