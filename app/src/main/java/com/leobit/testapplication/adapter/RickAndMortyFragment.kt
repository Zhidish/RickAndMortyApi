package com.leobit.testapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leobit.testapplication.databinding.MortyGridBinding
import com.leobit.testapplication.databinding.RecyclerViewBinding

class RickAndMortyFragment : Fragment() {

    val viewModel: RickAndMortyOverVIew by viewModels()
    val viewModel1: PlanetsOverView by viewModels()

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
                binding.viewModel = viewModel
                binding.mortyRecycler.adapter = GridAdapter()
                return binding.root
            }

            "Planets" -> {
                val bindingRecycler = RecyclerViewBinding.inflate(inflater)
                bindingRecycler.lifecycleOwner = this
                bindingRecycler.viewModel1 = viewModel1
                bindingRecycler.recyler.adapter = RecyclerAdapter()
                return bindingRecycler.root

            }
            else -> {
                val binding = MortyGridBinding.inflate(inflater)
                binding.lifecycleOwner = this
                binding.viewModel = viewModel
                binding.mortyRecycler.adapter = GridAdapter()
                return binding.root


            }


        }

    }


}