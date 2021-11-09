package com.leobit.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.os.Handler
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

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



        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.bottom)
        bottomNavigation.setupWithNavController(navController)

        (bottomNavigation).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.back -> {
                    onBackPressed()
                    true
                }

                R.id.home -> {
                    navController.navigate(R.id.rickAndMortyMenu)
                    true
                }

                else -> false
            }
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


}