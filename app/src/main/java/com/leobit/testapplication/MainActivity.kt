package com.leobit.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.bottom_navigation_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.bottom)
            bottomNavigation.setupWithNavController(navController)



        (bottomNavigation).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.back-> {onBackPressed()
                 true
                }

                R.id.home -> {navController.navigate(R.id.rickAndMortyMenu)
                true}

                else -> false
            }
        }


    }
}