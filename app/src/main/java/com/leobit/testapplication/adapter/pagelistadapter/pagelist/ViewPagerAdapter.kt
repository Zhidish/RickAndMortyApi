package com.leobit.testapplication.adapter.pagelistadapter.pagelist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager,val lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {

    val  fragmentList = mutableListOf<Fragment>()



    override fun getItemCount(): Int {
     return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
      return fragmentList[position]
    }
}