package com.example.pagingmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pagingmvvm.databinding.RickMortyLayoutBinding
import com.example.pagingmvvm.models.RickMorty

class RickMortyPagedAdapter :PagingDataAdapter<RickMorty,RickMortyPagedAdapter.MyViewHolder>(diffCallback){

    inner class MyViewHolder(val binding :RickMortyLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    companion object{
        val diffCallback = object :DiffUtil.ItemCallback<RickMorty>(){
            override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)

        //this method getItem() is from PagingDataAdapter..

        holder.binding.apply {
            textView.text = "${currentItem?.name}"
            val imageLink = currentItem?.image

            //here I'm using coroutine image loader(coil) to display images
            // but you can also use glide uf you want to
            imageView.load(imageLink){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RickMortyLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}