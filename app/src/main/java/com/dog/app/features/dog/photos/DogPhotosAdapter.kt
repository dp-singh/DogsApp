package com.dog.app.features.dog.photos

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class DogPhotosAdapter : ListAdapter<String, DogPhotoViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogPhotoViewHolder {
        return DogPhotoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DogPhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }
}