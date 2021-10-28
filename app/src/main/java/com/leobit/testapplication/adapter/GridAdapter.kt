package com.leobit.testapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leobit.testapplication.R
import com.leobit.testapplication.databinding.MortyGridBinding
import com.leobit.testapplication.databinding.MortyGridItemBinding
import com.leobit.testapplication.morty_menu.BounceInterpretator
import com.leobit.testapplication.network.CardObtainer
import com.leobit.testapplication.network.Character

class GridAdapter :
    ListAdapter<Character, GridAdapter.CharacterVIewHolder>(DiffCallBack) {


    class CharacterVIewHolder(
        var binding: MortyGridItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.character = character.copy()

            binding.executePendingBindings()
        }

    }


    companion object DiffCallBack : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVIewHolder {
        return CharacterVIewHolder(
            MortyGridItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }


    override fun onBindViewHolder(
        holder: CharacterVIewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val character = getItem(position)
        holder.binding.characterView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                // val bundle = bundleOf("id" to (position+1) )
                val animation = AnimationUtils.loadAnimation(holder.binding.root.context, R.anim.bounce)
                val bounce = BounceInterpretator(0.2, 20.0)
                animation.setInterpolator(bounce)
                holder.itemView.startAnimation(animation)

                val navigate = RickAndMortyFragmentDirections.actionRickAndMortyFragmentToDetail()
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