package com.leobit.testapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

import android.content.Intent
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.leobit.testapplication.adapter.RickAndMortyFragment
import com.leobit.testapplication.morty_menu.MenuAdapter
import com.leobit.testapplication.morty_menu.RickAndMortyMenuFragment


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var fragmentManagerActivity: FragmentManager = supportFragmentManager

    companion object {
     var currentPosition : Int = 0
        val KEY_CURRENT_POSITION :  String = "com.leobit.testapplication.key.currentPosition"

    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.bottom_navigation_view)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser


        if (currentUser == null) {
            val signInIntent = Intent(this, SigInActivity::class.java)
            Log.e("Current", "Current_USER")


        }

        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.bottom)



        (bottomNavigation).setOnItemSelectedListener {
            val newFragment: Fragment = RickAndMortyFragment()

            fragmentManagerActivity.popBackStack()
            fragmentManagerActivity.popBackStack()


            when (it.itemId) {
                R.id.chars -> {
                    val bundle = Bundle()
                    bundle.putString("destination", "Characters")
                    newFragment.arguments = bundle

                }

                R.id.locations -> {
                    val bundle = Bundle()
                    bundle.putString("destination", "Planets")
                    newFragment.arguments = bundle

                }

                else -> false
            }
            val fragmentManagerActivityTransactionButton = supportFragmentManager.beginTransaction()
            fragmentManagerActivityTransactionButton.setReorderingAllowed(true)
            fragmentManagerActivityTransactionButton.replace(R.id.fragment_container, newFragment)
            fragmentManagerActivityTransactionButton.addToBackStack(null)
            fragmentManagerActivityTransactionButton.commit()
            true

        }

         if(savedInstanceState!=null){
             currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION,0)

         }
    }

    override fun onResume() {
        super.onResume()
        val newFragment: Fragment = RickAndMortyFragment()
        newFragment.arguments.let {
            it?.putString("destination","Characters")
        }
        val fragmentManagerActivityTransactionButton = supportFragmentManager.beginTransaction()
        fragmentManagerActivityTransactionButton.setReorderingAllowed(true)
        fragmentManagerActivityTransactionButton.replace(R.id.fragment_container, newFragment)
        fragmentManagerActivityTransactionButton.addToBackStack(null)
        fragmentManagerActivityTransactionButton.commit()
    }



    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)


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