package com.example.testapp.features.breeddetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.databinding.ListItemDogPhotoBinding

class DogPhotoAdapter : ListAdapter<String, DogPhotoViewHolder>(DIFF_UTIL) {

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

class DogPhotoViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {
    private val binding = ListItemDogPhotoBinding.bind(containerView)
    fun bind(url: String) {
        binding.dogImageView.setImageResource(R.drawable.ic_launcher_background)
        Glide.with(containerView).load(url).centerCrop().into(binding.dogImageView)
    }

    companion object {
        fun create(parent: ViewGroup): DogPhotoViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_dog_photo, parent, false)
            return DogPhotoViewHolder(itemView)
        }
    }
}