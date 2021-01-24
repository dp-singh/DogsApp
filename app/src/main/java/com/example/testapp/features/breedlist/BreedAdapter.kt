package com.example.testapp.features.breedlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.DogType
import com.example.testapp.util.ViewEventListener

class BreedAdapter(val viewEvent: ViewEventListener<HomeViewEvents>) :
    ListAdapter<DogType, DogTypeViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogTypeViewHolder {
        return DogTypeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DogTypeViewHolder, position: Int) {
        holder.bind(getItem(position), viewEvent)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<DogType>() {
            override fun areItemsTheSame(oldItem: DogType, newItem: DogType): Boolean {
                return oldItem.subBreed == newItem.subBreed
            }

            override fun areContentsTheSame(oldItem: DogType, newItem: DogType): Boolean {
                return oldItem == newItem
            }
        }
    }
}