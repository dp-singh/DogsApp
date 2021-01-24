package com.example.testapp.features.breedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.DogType
import com.example.testapp.R
import com.example.testapp.databinding.ListItemDogTypeBinding
import com.example.testapp.util.ViewEventListener

class DogTypeViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView),
    ViewEventListener<HomeViewEvents> {
    private val binding = ListItemDogTypeBinding.bind(containerView)
    fun bind(dogType: DogType, viewEvent: ViewEventListener<HomeViewEvents>) {
        binding.breedTextView.text = getBreedText(dogType.subBreed, dogType.breed)
        binding.root.setOnClickListener {
            viewEvent.onViewEvent(HomeViewEvents.OpenDogPhotosScreen(dogType))
        }
    }

    private fun getBreedText(subBreed: String?, breed: String): String {
        return if (subBreed == null) {
            breed.capitalize()
        } else {
            breed.capitalize() + " " + subBreed.capitalize()
        }
    }

    companion object {
        fun create(parent: ViewGroup): DogTypeViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_dog_type, parent, false)
            return DogTypeViewHolder(itemView)
        }
    }

    override fun onViewEvent(viewEvents: HomeViewEvents) {
        when (viewEvents) {
            HomeViewEvents.Refresh -> TODO()
        }
    }
}