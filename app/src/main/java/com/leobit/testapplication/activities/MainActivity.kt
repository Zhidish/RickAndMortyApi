package com.leobit.testapplication.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.view.Menu
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.leobit.testapplication.R
import com.leobit.testapplication.fragments.CharacterFragment
import com.leobit.testapplication.fragments.FragmentLayout
import com.leobit.testapplication.fragments.LocationFragment
import com.leobit.testapplication.adapter.ViewPagerAdapter

/**
 *  MainActivity  for managing fragments by click listener
 *
 * */
class MainActivity : AppCompatActivity() {


    private var fragmentManagerActivity: FragmentManager = supportFragmentManager


    // static members for ViewPager
    companion object {
        val characterFragment = CharacterFragment()
        val locationFragment = LocationFragment()
        val fragmentLayout = FragmentLayout()
        lateinit var viewPager: ViewPager2
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewpager)
        val viewPagerAdapter = ViewPagerAdapter(fragmentManagerActivity, lifecycle)

        viewPagerAdapter.fragmentList.add(characterFragment)
        viewPagerAdapter.fragmentList.add(locationFragment)
        viewPager.adapter = viewPagerAdapter

        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.bottom)


        (bottomNavigation).setOnItemSelectedListener {

            when (it.itemId) {
                R.id.chars -> {
                    viewPager.setCurrentItem(0)

                }
                R.id.locations -> {
                    viewPager.setCurrentItem(1)

                }

                else ->
                    viewPager.setCurrentItem(0)
            }

            true

        }
        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if(position<2) {
                        bottomNavigation.menu.getItem(position).setChecked(true)
                    }
                }
            }
        )


    }

    @SuppressLint("WrongConstant")
    override fun onBackPressed() {
//        super.onBackPressed()
        var count = fragmentManagerActivity.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            fragmentManagerActivity.popBackStack()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = getMenuInflater()
        val menu = menuInflater.inflate(R.menu.sign_out_menu, menu)

        return true
    }

    @SuppressLint("ResourceType")
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.log_out)?.setOnMenuItemClickListener {
            val sigInIntent = Intent(this, AuthorizationActivity::class.java)
            Firebase.auth.signOut()
            startActivity(sigInIntent)
            true
        }

        return true
    }




}