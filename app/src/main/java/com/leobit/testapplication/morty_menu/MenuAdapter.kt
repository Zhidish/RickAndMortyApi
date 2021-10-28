package com.leobit.testapplication.morty_menu

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.leobit.testapplication.R
import com.leobit.testapplication.morty_menu.data.data

class MenuAdapter(
    private val context: Context?

) : RecyclerView.Adapter<MenuAdapter.MenuImage>() {
    var dataSource = data.items
    val detail = RickAndMortyMenuDirections.actionRickAndMortyMenuToRickAndMortyFragment("")


    class MenuImage(view: View?) : RecyclerView.ViewHolder(view!!) {
        var image = view?.findViewById<ImageView>(R.id.imageOfItem)
        var name = view?.findViewById<TextView>(R.id.nameOfItem)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuImage {
        return MenuImage(
            LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: MenuImage, @SuppressLint("RecyclerView") position: Int) {
        var item = dataSource[position]
        Log.d("holder", position.toString())

        holder.name?.text = item.name
        holder.image?.setImageResource(item.image)
        holder.image?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.e("click", "CLICK")
                val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)
                val bounce = BounceInterpretator(0.2, 20.0)
                animation.setInterpolator(bounce)
                holder.image!!.startAnimation(animation)

                // val detail =     RickAndMortyMenuDirections.actionRickAndMortyMenuToDetail()
                Log.e("name", holder.name?.text.toString())

                when (holder.name?.text.toString()) {

                    "Planets" -> if (v!= null){
                        detail.destination = "Planets"
                        v.findNavController().navigate(detail)
                    }

                    "Characters" -> if (v != null) {
                        detail.destination = "Characters"
                        v.findNavController().navigate(detail)
                    }


                }

            }

        }

        )

    }

    override fun getItemCount() = dataSource.size

}