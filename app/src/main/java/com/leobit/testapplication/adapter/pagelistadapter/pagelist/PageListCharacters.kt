package com.leobit.testapplication.adapter.pagelistadapter.pagelist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.leobit.testapplication.R
import com.leobit.testapplication.adapter.RickAndMortyFragmentDirections
import com.leobit.testapplication.network.Character

import com.leobit.testapplication.databinding.MortyGridItemBinding
import com.leobit.testapplication.morty_menu.BounceInterpretator

val CHARACTER_COMPORATOR = object :
    DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }


}


class PagindListCharacterAdapter :
    PagingDataAdapter<Character, PagindListCharacterAdapter.CharacterViewHolder>(CHARACTER_COMPORATOR) {

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

    override fun onBindViewHolder(holder: CharacterViewHolder,
                                  @SuppressLint("RecyclerView") position: Int) {
        val character = getItem(position)

        if (character != null) {

            holder.binding.characterView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {

                    // val bundle = bundleOf("id" to (position+1) )

                    val animation =
                        AnimationUtils.loadAnimation(holder.binding.root.context, R.anim.bounce)
                    val bounce = BounceInterpretator(0.2, 20.0)

                    animation.setInterpolator(bounce)
                    holder.itemView.startAnimation(animation)

                    val navigate =
                        RickAndMortyFragmentDirections.actionRickAndMortyFragmentToDetail()
                    navigate.id = (position + 1)

                    if (v != null) {
                        v.findNavController().navigate(navigate)
                    }

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

