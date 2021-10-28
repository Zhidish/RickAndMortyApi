package com.leobit.testapplication.morty_menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.leobit.testapplication.R
import com.leobit.testapplication.databinding.ActivityMainBinding.inflate
import com.leobit.testapplication.databinding.BottomNavigationViewBinding.inflate
import com.leobit.testapplication.databinding.RickAndMortyMenuBinding

class RickAndMortyMenu : Fragment() {

    lateinit var rickAndMortyMenu: RickAndMortyMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        rickAndMortyMenu = RickAndMortyMenuBinding.inflate(inflater)
        rickAndMortyMenu.menuGrid.adapter = MenuAdapter(context)

       return rickAndMortyMenu.root
    }




}