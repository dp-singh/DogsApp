package com.dog.app.features.dog.photos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dog.domain.model.Resource
import com.dog.app.R
import com.dog.app.databinding.FragmentDogTypePhotosBinding
import com.dog.app.util.DefaultErrorHandler
import com.dog.app.util.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class DogPhotosFragment : Fragment(R.layout.fragment_dog_type_photos) {
    private val dogTypeArgs: DogPhotosFragmentArgs by navArgs()
    private val viewModel by viewModel<DogPhotosViewModel> {
        parametersOf(dogTypeArgs.dog)
    }
    private val binding by viewBinding(FragmentDogTypePhotosBinding::bind)
    private val dogPhotosAdapter = DogPhotosAdapter()
    private val errorHandler by inject<DefaultErrorHandler>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onViewEvent(DogPhotosViewEvent.TryAgain)
        }
        binding.errorView.tryAgainButton.setOnClickListener {
            viewModel.onViewEvent(DogPhotosViewEvent.Refresh)
        }
        binding.dogPhotosRecyclerView.apply {
            setHasFixedSize(true)
            adapter = dogPhotosAdapter
        }
        (activity as AppCompatActivity).supportActionBar?.title = getToolbarTitle()
    }

    private fun getToolbarTitle(): String {
        return getString(
            R.string.title_dog_breed_photos,
            dogTypeArgs.dog.breed.capitalize(Locale.getDefault()),
            dogTypeArgs.dog.subBreed.orEmpty().capitalize(Locale.getDefault())
        )
    }

    private fun handleViewState(result: Resource<List<String>>) {
        with(binding) {
            when (result) {
                Resource.Loading -> {
                    swipeRefreshLayout.isRefreshing = true
                    errorView.root.visibility = View.GONE
                }
                is Resource.Success -> {
                    swipeRefreshLayout.isRefreshing = false
                    dogPhotosRecyclerView.visibility = View.VISIBLE
                    errorView.root.visibility = View.GONE
                    dogPhotosAdapter.submitList(result.data)
                }
                is Resource.SuccessFromCache -> {
                    swipeRefreshLayout.isRefreshing = false
                    dogPhotosRecyclerView.visibility = View.VISIBLE
                    errorView.root.visibility = View.GONE
                    dogPhotosAdapter.submitList(result.data)
                    showError(result.throwable)
                }
                is Resource.Error -> {
                    swipeRefreshLayout.isRefreshing = false
                    dogPhotosRecyclerView.visibility = View.GONE
                    errorView.root.visibility = View.VISIBLE
                    errorView.errorTextView.text = errorHandler.getMessage(result.throwable)
                }
            }
        }
    }

    private fun showError(throwable: Throwable) {
        Snackbar
            .make(binding.root, errorHandler.getMessage(throwable), Snackbar.LENGTH_LONG)
            .show()
    }
}
