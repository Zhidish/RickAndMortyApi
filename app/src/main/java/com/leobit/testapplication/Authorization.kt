package com.leobit.testapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


/**
 * First activity loaded for further determinding for managing Activity
 */

class Authorization : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        //if user are not logged
        if (user == null) {
                val signInActivity  = Intent(this,SigInActivity::class.java)
                signInActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(signInActivity)
        //if user are already logged in
        } else {
            val mainActivity = Intent(this, MainActivity::class.java)
               mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(mainActivity)
        }

    }
}