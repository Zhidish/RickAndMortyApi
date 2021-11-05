package com.leobit.testapplication.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


import coil.load

import com.leobit.testapplication.network.Character
import com.leobit.testapplication.network.Location

@BindingAdapter("listData")
fun binRecyclerView(recyclerView: RecyclerView, data: List<Location>?) {
   val adapter = recyclerView.adapter as RecyclerAdapter
    adapter.submitList(data)

}

@BindingAdapter("listDataCharacter")
fun bindGridItem(recyclerView: RecyclerView, data : List<Character>?){
    val adapter = recyclerView.adapter as? GridAdapter
    if (adapter != null) {
        adapter.submitList(data)
    }
}


@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl : String?) {

    imageUrl?.let{
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUri)

    }

}
