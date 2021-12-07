package com.leobit.testapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.os.PersistableBundle
import android.view.FrameMetrics
import android.view.Menu
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.leobit.testapplication.adapter.CharacterFragment
import com.leobit.testapplication.adapter.FragmentLayout
import com.leobit.testapplication.adapter.LocationFragment
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.ViewPagerAdapter

/**
 *  MainActivity  for managing fragments by click listener
 *
 * */
class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var fragmentManagerActivity: FragmentManager = supportFragmentManager
    private lateinit var viewPager : ViewPager2


    // static members for ViewPager
    companion object {
        var currentPosition: Int = 0
        val KEY_CURRENT_POSITION: String = "com.leobit.testapplication.key.currentPosition"
        var oldFragment: Fragment? = null
        val characterFragment = CharacterFragment()
       val locationFragment =  LocationFragment()
        val fragmentLayout = FragmentLayout()
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.bottom_navigation_view)

        viewPager=  findViewById(R.id.viewpager)
        val viewPagerAdapter  = ViewPagerAdapter(fragmentManagerActivity,lifecycle)
        viewPagerAdapter.fragmentList.add(fragmentLayout)
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
                   bottomNavigation.menu.getItem(position).setChecked(true)

                }
            }
        )






    }

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
            val sigInIntent = Intent(this, Authorization::class.java)
            Firebase.auth.signOut()
            startActivity(sigInIntent)
            true
        }

        return true
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(KEY_CURRENT_POSITION, currentPosition)

    }


}