package com.leobit.testapplication.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import coil.load
import com.leobit.testapplication.R

class DetailActivity : AppCompatActivity() {


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail)

        val text = intent.getStringExtra("CharacterText")
        val name = intent.getStringExtra("CharacterName")
        val uri = intent.getStringExtra("CharacterImage")


        window.sharedElementEnterTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.image_shared_element_transition)
        findViewById<TextView>(R.id.idCharacter).text = text
        val detailBinding = findViewById<ImageView>(R.id.sharedImage)
        detailBinding.transitionName = name

        detailBinding.let {
            val imgUrl = uri?.toUri()?.buildUpon()?.scheme("https")?.build()
            it.load(imgUrl) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }

    }


}