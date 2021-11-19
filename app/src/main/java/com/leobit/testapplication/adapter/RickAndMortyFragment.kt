package com.leobit.testapplication.adapter

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.PagindListCharacterAdapter
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.PagingListLocationsAdapter
import com.leobit.testapplication.databinding.CharactersRecyclerBinding
import com.leobit.testapplication.databinding.PlanetsRecyclerBinding
import com.leobit.testapplication.network.Character
import com.leobit.testapplication.network.Characters
import com.leobit.testapplication.network.Location
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RickAndMortyFragment : Fragment() {
    lateinit var charactersViewModel: CharactersViewModel
    lateinit var planetViewModel: PlanetsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //RickAndMortyFragmentArgs
        //n your receiving destination’s code, use the getArguments() method to retrieve
        // the bundle and use its contents.
        // When using the -ktx dependencies, Kotlin users can also use the by navArgs() property delegate to access arguments.

        charactersViewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)



        planetViewModel = ViewModelProvider(this).get(PlanetsViewModel::class.java)



        when (arguments?.getString("destination")) {
            "Characters" -> {

                val binding = CharactersRecyclerBinding.inflate(inflater)
                binding.lifecycleOwner = this
                binding.viewModel = charactersViewModel
//                binding.mortyRecycler.adapter = GridAdapter()

                val pagindAdapter = context?.let { PagindListCharacterAdapter(it) }
                binding.mortyRecycler.adapter = pagindAdapter

                lifecycleScope.launch {
                    charactersViewModel.flow.collectLatest { value: PagingData<Character> ->
                        pagindAdapter!!.submitData(value)
                    }

                }
                return binding.root
            }

            "Planets" -> {
                    Log.e("Planets", "in Planets")
                val binding = PlanetsRecyclerBinding.inflate(inflater)
                binding.lifecycleOwner = this


                val pagindAdapter = PagingListLocationsAdapter()

                binding.recyler.adapter = pagindAdapter

                lifecycleScope.launch {
                    planetViewModel.flow.collectLatest { value: PagingData<Location> ->
                        pagindAdapter.submitData(value)
                    }

                }


                return binding.root

            }


            else -> {
                val binding =CharactersRecyclerBinding.inflate(inflater)
                binding.lifecycleOwner = this
                binding.viewModel = charactersViewModel


                val pagindAdapter = context?.let { PagindListCharacterAdapter(it) }
                binding.mortyRecycler.adapter = pagindAdapter

                lifecycleScope.launch {
                    charactersViewModel.flow.collectLatest { value: PagingData<Character> ->
                        pagindAdapter!!.submitData(value)
                    }

                }
                return binding.root


            }


        }

    }


}