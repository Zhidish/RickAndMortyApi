package com.leobit.testapplication.adapter

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.leobit.testapplication.R
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.PagindListCharacterAdapter
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.PagingListLocationsAdapter
import com.leobit.testapplication.databinding.CharactersRecyclerBinding
import com.leobit.testapplication.databinding.PlanetsRecyclerBinding
import com.leobit.testapplication.network.Character
import com.leobit.testapplication.network.Location
import kotlinx.coroutines.flow.collectLatest
import com.leobit.testapplication.MainActivity
import kotlinx.coroutines.launch




/**
 * Fragment for displaying RecyclerView for character and locations
 *
 * */
class CharacterFragment : Fragment() {
    lateinit var charactersViewModel: CharactersViewModel
    lateinit var characterRecycler: CharactersRecyclerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        charactersViewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)



                val binding = CharactersRecyclerBinding.inflate(inflater)
                binding.lifecycleOwner = this
                binding.viewModel = charactersViewModel
                this.exitTransition = TransitionInflater.from(context)
                    .inflateTransition(R.transition.exit_grid_transition)
                val pagindAdapter = context?.let { PagindListCharacterAdapter(it, this) }
                binding.mortyRecycler.adapter = pagindAdapter

                lifecycleScope.launch {
                    charactersViewModel.flow.collectLatest { value: PagingData<Character> ->
                        pagindAdapter!!.submitData(value)
                    }
                }
                characterRecycler = binding

                return binding.root
            }









    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()

        (view.parent as? ViewGroup)?.doOnPreDraw {
            startPostponedEnterTransition()
        }

    }

    fun prepareTransition(): Unit {
        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.exit_grid_transition)
    }


}