package com.example.testapp.features.breeddetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.Resource
import com.example.testapp.R
import com.example.testapp.databinding.FragmentDogTypePhotosBinding
import com.example.testapp.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DogTypePhotosFragment : Fragment(R.layout.fragment_dog_type_photos) {
    private val dogTypeArgs: DogTypePhotosFragmentArgs by navArgs()
    private val viewModel by viewModel<DogBreedPhotosViewModel> {
        parametersOf(dogTypeArgs.dogType)
    }
    private val binding by viewBinding(FragmentDogTypePhotosBinding::bind)
    private val dogPhotoAdapter = DogPhotoAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)
        binding.breedTypePhotosRecyclerView.apply {
            adapter = dogPhotoAdapter
        }
        (activity as AppCompatActivity).supportActionBar?.title = getToolbarTitle()
    }

    private fun getToolbarTitle(): String {
        return getString(
            R.string.title_dog_breed_photos,
            dogTypeArgs.dogType.breed.capitalize(),
            dogTypeArgs.dogType.subBreed.orEmpty().capitalize()
        )
    }

    private fun handleViewState(result: Resource<List<String>>) = when (result) {
        Resource.Loading -> {
        }
        is Resource.Success -> {
            dogPhotoAdapter.submitList(result.data)
        }
        is Resource.Error -> {
        }
    }
}