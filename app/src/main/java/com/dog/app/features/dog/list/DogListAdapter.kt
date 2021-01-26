package com.dog.app.features.dog.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dog.domain.model.Dog
import com.dog.app.util.ViewEventListener

class DogListAdapter(private val viewEvent: ViewEventListener<DogListViewEvents>) :
    ListAdapter<Dog, DogViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(getItem(position), viewEvent)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Dog>() {
            override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
                return oldItem.subBreed == newItem.subBreed
            }

            override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
                return oldItem == newItem
            }
        }
    }
}