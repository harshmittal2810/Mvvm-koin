package com.harsh.home.views

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.harsh.home.CategoryFragment
import com.harsh.model.Master

class FragmentSectionsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
  private var masterList: MutableList<Master> = mutableListOf()

  override fun getItem(position: Int): Fragment {
    Log.e(
      "FragmentSection",
      "Data ${masterList[position].data} Name ${masterList[position].name}"
    )
    return CategoryFragment.newInstance(masterList[position])
  }

  fun prepareTabs(list: MutableList<Master>) {
    masterList.clear()
    masterList.addAll(list)
    notifyDataSetChanged()
  }

  override fun getCount(): Int {
    return masterList.size
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return masterList[position].name
  }
}