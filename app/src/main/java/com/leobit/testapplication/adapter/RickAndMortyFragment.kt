package com.leobit.testapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.PagindListCharacterAdapter
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.PagingListLocationsAdapter
import com.leobit.testapplication.databinding.MortyGridBinding
import com.leobit.testapplication.databinding.RecyclerViewBinding
import com.leobit.testapplication.network.Character
import com.leobit.testapplication.network.Location
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RickAndMortyFragment : Fragment() {
    val charactersViewModel: CharactersViewModel by viewModels()
    val planetViewModel: PlanetsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //RickAndMortyFragmentArgs
        //n your receiving destination’s code, use the getArguments() method to retrieve
        // the bundle and use its contents.
        // When using the -ktx dependencies, Kotlin users can also use the by navArgs() property delegate to access arguments.


        when (arguments?.getString("destination")) {
            "Characters" -> {

                val binding = MortyGridBinding.inflate(inflater)
                binding.lifecycleOwner = this
                binding.viewModel = charactersViewModel
//                binding.mortyRecycler.adapter = GridAdapter()

                val pagindAdapter = PagindListCharacterAdapter()
                binding.mortyRecycler.adapter = pagindAdapter

                lifecycleScope.launch {
                    charactersViewModel.flow.collectLatest { value: PagingData<Character> ->
                        pagindAdapter.submitData(value)
                    }

                }
                return binding.root
            }

            "Planets" -> {

                val binding = RecyclerViewBinding.inflate(inflater)
                binding.lifecycleOwner = this
                binding.viewModel1 = planetViewModel

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
                val binding = MortyGridBinding.inflate(inflater)
                binding.lifecycleOwner = this
                binding.viewModel = charactersViewModel


                val pagindAdapter = PagindListCharacterAdapter()
                binding.mortyRecycler.adapter = pagindAdapter

                lifecycleScope.launch {
                    charactersViewModel.flow.collectLatest { value: PagingData<Character> ->
                        pagindAdapter.submitData(value)
                    }

                }
                return binding.root





            }


        }

    }


}