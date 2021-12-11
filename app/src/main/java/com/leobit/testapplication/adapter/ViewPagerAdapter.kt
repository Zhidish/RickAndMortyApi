package com.leobit.testapplication.adapter

import android.util.ArrayMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {


    private val mapOfFragment = ArrayMap<Int, Fragment>()




    val  fragmentList = mutableListOf<Fragment>()


    override fun getItemCount(): Int {
     return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
      return fragmentList[position]
    }

    fun addFragment(fragment : Fragment){

        fragmentList.add(fragment)

    }





}