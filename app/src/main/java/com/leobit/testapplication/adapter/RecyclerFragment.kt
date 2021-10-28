package com.leobit.testapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.leobit.testapplication.databinding.RecyclerViewBinding

class RecyclerFragment : Fragment() {

    private val viewModel: CardOverView by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RecyclerViewBinding.inflate(inflater)
       /* binding.lifecycleOwner = this
        binding.viewModel1 = viewModel
        binding.recyler.adapter = RecyclerAdapter()*/
        return binding.root
    }
}




