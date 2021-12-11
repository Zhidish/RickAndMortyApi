package com.leobit.testapplication.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.alpha
import com.google.firebase.auth.FirebaseAuth
import com.leobit.testapplication.R


/**
 * First activity loaded for further determinding for managing Activity
 */

class AuthorizationActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

            //if user are not logged
            if (user == null) {
                val signInActivity = Intent(this, SigInActivity::class.java)
                signInActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                finish()
                startActivity(signInActivity)

                //if user are already logged in
            } else {
                val mainActivity = Intent(this, MainActivity::class.java)
                mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                finish()
                startActivity(mainActivity)

            }



    }

    override fun onResume() {
        super.onResume()

        finish()
    }




}