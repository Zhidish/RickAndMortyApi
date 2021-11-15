package com.leobit.testapplication.adapter.pagelistadapter.pagelist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.leobit.testapplication.R
import com.leobit.testapplication.adapter.RickAndMortyFragment

import com.leobit.testapplication.databinding.GridItemBinding
import com.leobit.testapplication.network.Character

import com.leobit.testapplication.databinding.MortyGridItemBinding
import com.leobit.testapplication.detail
import com.leobit.testapplication.details
import com.leobit.testapplication.morty_menu.BounceInterpretator
import com.leobit.testapplication.morty_menu.RickAndMortyMenuFragment
import com.leobit.testapplication.network.Location


// object for tracking changes for echa grid item
val CHARACTER_COMPORATOR = object :
    DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }


}


val LOCATION_COMPORATOR = object :
    DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }


}


class PagindListCharacterAdapter(var context: Context) :
    PagingDataAdapter<Character, PagindListCharacterAdapter.CharacterViewHolder>(
        CHARACTER_COMPORATOR
    ) {
    var fragmentManger: FragmentManager = (context as AppCompatActivity).supportFragmentManager

    class CharacterViewHolder(
        var binding: MortyGridItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.character = character.copy()
            //obtaining image using Coil
            binding.characterView.let {
                val imgUrl = character.image.toUri().buildUpon().scheme("https").build()
                it.load(imgUrl)
            }
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val character = getItem(position)

        if (character != null) {

            holder.binding.characterView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {




                                val bundle =  Bundle()
                    bundle.putString("characterText",holder.binding.character.toString())


                    val animation =
                        AnimationUtils.loadAnimation(holder.binding.root.context, R.anim.bounce)
                    val bounce = BounceInterpretator(0.2, 20.0)

                    animation.setInterpolator(bounce)
                    holder.itemView.startAnimation(animation)
                    var transaction = fragmentManger.beginTransaction()
                    transaction.remove(rickAndMortyFragment).commit()

                    var details = details()

                    details.arguments=bundle
                    details.arguments?.getString("characterText")?.let {
                        Log.e("PagindLocationAdapter",
                            it
                        )
                    }
                    transaction = fragmentManger.beginTransaction()
                    transaction.add(R.id.fragment_container,details)
                    transaction.commit()

                }
            }
            )

            holder.bind(character)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(MortyGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    companion object {

        lateinit var rickAndMortyFragment: RickAndMortyFragment

    }
}

class PagingListLocationsAdapter :
    PagingDataAdapter<Location, PagingListLocationsAdapter.LocationViewModel>(
        LOCATION_COMPORATOR
    ) {

    class LocationViewModel(
        var binding: GridItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(location: Location) {
            binding.planet = location.copy()
            //

            binding.gridItem.text = with(location) {
                "Planet name : ${location.name}\nPlanet type : ${location.type}\n" +
                        "Planet dimension : ${location.dimension}\n"
            }


            binding.executePendingBindings()
        }

    }

    override fun onBindViewHolder(holder: LocationViewModel, position: Int) {
        val location = getItem(position)
        if (location != null) {

            holder.bind(location)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewModel {
        return LocationViewModel(
            GridItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }


}





