package com.dog.app.features.dog.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dog.domain.model.Dog
import com.dog.app.R
import com.dog.app.databinding.ListItemDogTypeBinding
import com.dog.app.util.ViewEventListener
import java.util.*

class DogViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
    private val binding = ListItemDogTypeBinding.bind(containerView)
    fun bind(dog: Dog, viewEvent: ViewEventListener<DogListViewEvents>) {
        binding.breedTextView.text = getTitle(dog)
        binding.root.setOnClickListener {
            viewEvent.onViewEvent(DogListViewEvents.OpenDogPhotosScreen(dog))
        }
    }

    private fun getTitle(dog: Dog): String {
        return binding.root.context.getString(
            R.string.title_dog_breed_photos,
            dog.breed.capitalize(Locale.getDefault()),
            dog.subBreed.orEmpty().capitalize(Locale.getDefault())
        )
    }

    companion object {
        fun create(parent: ViewGroup): DogViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_dog_type, parent, false)
            return DogViewHolder(itemView)
        }
    }
}