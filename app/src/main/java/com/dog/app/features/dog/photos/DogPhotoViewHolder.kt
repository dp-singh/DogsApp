package com.dog.app.features.dog.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dog.app.R
import com.dog.app.databinding.ListItemDogPhotoBinding

class DogPhotoViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {
    private val binding = ListItemDogPhotoBinding.bind(containerView)
    fun bind(url: String) {
        Glide.with(containerView)
            .load(url)
            .centerCrop()
            .error(R.drawable.ic_error)
            .into(binding.dogImageView)
    }

    companion object {
        fun create(parent: ViewGroup): DogPhotoViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_dog_photo, parent, false)
            return DogPhotoViewHolder(itemView)
        }
    }
}