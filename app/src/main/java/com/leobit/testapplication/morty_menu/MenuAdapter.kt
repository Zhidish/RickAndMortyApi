package com.leobit.testapplication.morty_menu

import android.annotation.SuppressLint
import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction

import androidx.recyclerview.widget.RecyclerView
import com.leobit.testapplication.R
import com.leobit.testapplication.adapter.RickAndMortyFragment
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.PagindListCharacterAdapter
import com.leobit.testapplication.morty_menu.data.data

class MenuAdapter(
    private val context: Context?

) : RecyclerView.Adapter<MenuAdapter.MenuImage>() {
    var dataSource = data.items
    var fragmentManager = (context as AppCompatActivity).supportFragmentManager


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
        var fragmentTransaction = fragmentManager.beginTransaction()


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

                    "Planets" -> if (v != null) {
                        //initializing budle for passing key destination
                        val bundle = Bundle()
                        Log.e("MenuAdapter", "MenuAdapter")
                        bundle.putString("destination", "Planets")
                        //initializing fragmnet
                        val rickAndMortyFragment = RickAndMortyFragment()
                        rickAndMortyFragment.arguments = bundle

                   //     fragmentRickAndMortyMenu?.let { fragmentTransaction.remove(it).commit() }

                        fragmentTransaction = fragmentManager.beginTransaction()
                        //  Log.e("Planets", "Planets")
                        fragmentTransaction.add(R.id.fragment_container, rickAndMortyFragment)
                        fragmentTransaction.addToBackStack(null)

                            .commit()

                    }

                    "Characters" -> if (v != null) {

                        val bundle = Bundle()
                        bundle.putString("destination", "Characters")
                        //initializing fragmnet
                        val rickAndMortyFragment = RickAndMortyFragment()
                        PagindListCharacterAdapter.rickAndMortyFragment = rickAndMortyFragment
                        rickAndMortyFragment.arguments = bundle

                   //     fragmentRickAndMortyMenu?.let {
                 //           fragmentTransaction.remove(it).commit()
                    //    }

                        fragmentTransaction = fragmentManager.beginTransaction()

                        fragmentTransaction.add(R.id.fragment_container, rickAndMortyFragment)

                        fragmentTransaction.addToBackStack(null)
                            .commit()

                    }


                }

            }

        }

        )

    }

    override fun getItemCount() = dataSource.size

    companion object {
        var fragmentRickAndMortyMenu: RickAndMortyMenuFragment? = null


    }
}