package com.leobit.testapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Authorization : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser


        if (user == null) {
                val signInActivity  = Intent(this,SigInActivity::class.java)
                startActivity(signInActivity)
        } else {
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
        }

    }
}