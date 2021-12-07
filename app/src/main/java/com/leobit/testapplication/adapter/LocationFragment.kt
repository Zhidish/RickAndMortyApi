package com.leobit.testapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.PagingListLocationsAdapter
import com.leobit.testapplication.databinding.PlanetsRecyclerBinding
import com.leobit.testapplication.network.Location
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationFragment : Fragment() {

    lateinit var planetViewModel: PlanetsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        planetViewModel = ViewModelProvider(this).get(PlanetsViewModel::class.java)

        val binding = PlanetsRecyclerBinding.inflate(inflater)
        binding.lifecycleOwner = this


        val pagindAdapter = context?.let { PagingListLocationsAdapter(it) }

        binding.recyler.adapter = pagindAdapter

        lifecycleScope.launch {
            planetViewModel.flow.collectLatest { value: PagingData<Location> ->
                pagindAdapter?.submitData(value)
            }

        }

        return binding.root


    }


}