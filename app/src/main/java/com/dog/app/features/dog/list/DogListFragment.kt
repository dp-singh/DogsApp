package com.dog.app.features.dog.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dog.domain.model.Dog
import com.dog.domain.model.Resource
import com.dog.app.NavGraphDirections
import com.dog.app.R
import com.dog.app.databinding.FragmentMainBinding
import com.dog.app.util.DefaultErrorHandler
import com.dog.app.util.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DogListFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModel<DogListViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val dogAdapter by lazy { DogListAdapter(viewModel) }
    private val errorHandler by inject<DefaultErrorHandler>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)
        viewModel.singleViewState.observe(viewLifecycleOwner, ::handleSingleViewState)
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onViewEvent(DogListViewEvents.TryAgain)
        }
        binding.errorView.tryAgainButton.setOnClickListener {
            viewModel.onViewEvent(DogListViewEvents.Refresh)
        }
        binding.dogsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = dogAdapter
        }
    }

    private fun handleSingleViewState(singleViewState: DogListSingleViewState) {
        when (singleViewState) {
            is DogListSingleViewState.NavToDogPhotos -> findNavController().navigate(
                NavGraphDirections.actionProvideDogTypePhotosFragment(singleViewState.dog)
            )
        }
    }

    private fun handleViewState(result: Resource<List<Dog>>) {
        with(binding) {
            when (result) {
                Resource.Loading -> {
                    swipeRefreshLayout.isRefreshing = true
                    errorView.root.visibility = View.GONE
                }
                is Resource.Success -> {
                    swipeRefreshLayout.isRefreshing = false
                    dogsRecyclerView.visibility = View.VISIBLE
                    errorView.root.visibility = View.GONE
                    dogAdapter.submitList(result.data)
                }
                is Resource.SuccessFromCache -> {
                    swipeRefreshLayout.isRefreshing = false
                    dogsRecyclerView.visibility = View.VISIBLE
                    errorView.root.visibility = View.GONE
                    dogAdapter.submitList(result.data)
                    showError(result.throwable)
                }
                is Resource.Error -> {
                    swipeRefreshLayout.isRefreshing = false
                    dogsRecyclerView.visibility = View.GONE
                    errorView.root.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showError(throwable: Throwable) {
        Snackbar.make(binding.root, errorHandler.getMessage(throwable), Snackbar.LENGTH_LONG)
            .setAction(R.string.try_again) {
                viewModel.onViewEvent(DogListViewEvents.Refresh)
            }.show()
    }
}
