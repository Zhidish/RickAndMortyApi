package com.leobit.testapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.leobit.testapplication.adapter.RickAndMortyFragment
import com.leobit.testapplication.morty_menu.MenuAdapter
import com.leobit.testapplication.morty_menu.RickAndMortyMenuFragment


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var fragmentManagerActivity: FragmentManager = supportFragmentManager

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


            //   startActivity(signInIntent)
            /*  finish();
              return;*/
        }
        //initializing FragmentManger
        val rickAndMortyMenuFragment = RickAndMortyMenuFragment()
        MenuAdapter.fragmentRickAndMortyMenu = rickAndMortyMenuFragment


        val fragmentManagerActivityTransaction = supportFragmentManager.beginTransaction()

        fragmentManagerActivityTransaction.add(R.id.fragment_container, rickAndMortyMenuFragment)
        fragmentManagerActivityTransaction.commit()

        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.bottom)
/*

        val toLogOut = findViewById<View>(R.id.log_out)

        toLogOut.setOnClickListener{
            val sigInIntent = Intent(this,SigInActivity::class.java)
            startActivity(sigInIntent)
            finish()



        }
*/



        (bottomNavigation).setOnItemSelectedListener {
            val newFragment: Fragment = RickAndMortyFragment()

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

            fragmentManagerActivityTransactionButton.add(R.id.fragment_container, newFragment)
            fragmentManagerActivityTransactionButton.addToBackStack(null)
            fragmentManagerActivityTransactionButton.commit()
            true

        }


    }


    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)


        /* updateUI(account)*/


    }

    private fun signIn() {
        /*val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)*/
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
                        val sigInIntent = Intent(this,Authorization::class.java)
                        FirebaseAuth.getInstance().signOut();
                        startActivity(sigInIntent)
                        true
                    }
        return true
    }
}