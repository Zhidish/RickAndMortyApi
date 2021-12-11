package com.leobit.testapplication.adapter.pagelistadapter.pagelist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.leobit.testapplication.R
import com.leobit.testapplication.databinding.GridItemBinding
import com.leobit.testapplication.network.Character
import com.leobit.testapplication.databinding.MortyGridItemBinding
import com.leobit.testapplication.fragments.DetailsFragment
import com.leobit.testapplication.activities.MainActivity
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


class PagindListCharacterAdapter(var context: Context, var fragment: Fragment) :
    PagingDataAdapter<Character, PagindListCharacterAdapter.CharacterViewHolder>(
        CHARACTER_COMPORATOR
    ) {
    var fragmentManger: FragmentManager = (context as AppCompatActivity).supportFragmentManager

    class CharacterViewHolder(
        var binding: MortyGridItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {

            binding.character = character.copy()
            //using Coil for obtaining image and putting placeholder and error vector asset
            binding.characterView.let {
                val imgUrl = character.image.toUri().buildUpon().scheme("https").build()
                it.load(imgUrl) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
            }
            ViewCompat.setTransitionName(binding.characterView, character.name)
            binding.executePendingBindings()
        }
    }

    /**Binding image with click listener and adding some animation for click action
     */
    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {

        val character = getItem(position)
        if (character != null) {
            holder.binding.characterView.setOnClickListener(object : View.OnClickListener{
                @SuppressLint("WrongConstant")
                override fun onClick(v: View?) {

                    val bundle = Bundle()
                    bundle.putString("characterText", holder.binding.character.run {
                        "Character name : ${this?.name}\n Character status : ${this?.status}\n" +
                                "Character origin : ${this?.origin}\n Character gender : ${this?.gender}"
                    })
                    bundle.putString("characterName", holder?.binding?.character?.name)
                    bundle.putString("characterImage", holder?.binding?.character?.image)

                    var details = DetailsFragment()
                    val transitionalView = v?.findViewById<ImageView>(R.id.characterView)
                    details.arguments = bundle

                   (fragment.exitTransition as TransitionSet).excludeTarget(v, true)
                    var transaction = fragmentManger.beginTransaction()
                    transaction.setReorderingAllowed(true)

                    if (transitionalView != null) {
                        transaction.addSharedElement(
                            transitionalView,
                            holder!!.binding!!.character!!.name
                        )

                        Log.e("Name_1", holder!!.binding!!.character!!.name)
                    }
                   /* (MainActivity.viewPager.adapter as ViewPagerAdapter).addFragment(details)
                    MainActivity.viewPager.setCurrentItem(2,false)*/
                    transaction.add(R.id.recycler_fragment, details)
                    transaction.addToBackStack(null)
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

}

class PagingListLocationsAdapter(var context: Context) :
    PagingDataAdapter<Location, PagingListLocationsAdapter.LocationViewModel>(
        LOCATION_COMPORATOR
    ) {
    var fragmentManger: FragmentManager = (context as AppCompatActivity).supportFragmentManager

    class LocationViewModel(
        var binding: GridItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(location: Location) {

            binding.gridItem.text = ("Planet name : ${location.name}\nPlanet type : ${location.type}\n" +
                    "Planet dimension : ${location.dimension}\n")

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





