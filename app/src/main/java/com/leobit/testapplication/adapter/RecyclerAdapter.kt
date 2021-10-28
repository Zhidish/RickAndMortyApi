package com.leobit.testapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leobit.testapplication.databinding.GridItemBinding
import com.leobit.testapplication.databinding.RecyclerViewBinding
import com.leobit.testapplication.network.Location

class RecyclerAdapter :
    ListAdapter<Location, RecyclerAdapter.CardVIewHolder>(DiffCallBack) {


    class CardVIewHolder(
        private var binding: GridItemBinding

    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {

            binding.gridItem.text = with(location){
                "Planet name : ${location.name}\nPlanet type : ${location.type}\n" +
                "Planet dimension : ${location.dimension}\n"

            }

        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id==newItem.id
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardVIewHolder {
        return CardVIewHolder(
            GridItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }


    override fun onBindViewHolder(holder: CardVIewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)


    }


}