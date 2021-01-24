package com.example.testapp.features.breedlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.domain.model.DogType
import com.example.domain.model.Resource
import com.example.testapp.NavGraphDirections
import com.example.testapp.R
import com.example.testapp.databinding.FragmentMainBinding
import com.example.testapp.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_main) {
    private val homeViewModel by viewModel<HomeViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val breedAdapter by lazy { BreedAdapter(homeViewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)
        homeViewModel.singleViewState.observe(viewLifecycleOwner, ::handleSingleViewState)
        binding.swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.onViewEvent(HomeViewEvents.Refresh)
        }
        binding.errorView.tryAgainButton.setOnClickListener {
            homeViewModel.onViewEvent(HomeViewEvents.Refresh)
        }
        binding.dogTypeRecyclerView.apply {
            setHasFixedSize(true)
            adapter = breedAdapter
        }
    }

    private fun handleSingleViewState(singleViewState: HomeSingleViewState) {
        when (singleViewState) {
            is HomeSingleViewState.NavToDogPhotosFragment -> findNavController().navigate(
                NavGraphDirections.actionProvideDogTypePhotosFragment(singleViewState.dogType)
            )
        }
    }

    private fun handleViewState(result: Resource<List<DogType>>) = when (result) {
        Resource.Loading -> {
            binding.swipeRefreshLayout.isRefreshing = true
            binding.errorView.root.visibility = View.GONE
        }
        is Resource.Success -> {
            binding.swipeRefreshLayout.isRefreshing = false
            binding.dogTypeRecyclerView.visibility = View.VISIBLE
            binding.errorView.root.visibility = View.GONE
            breedAdapter.submitList(result.data)
        }
        is Resource.Error -> {
            binding.swipeRefreshLayout.isRefreshing = false
            binding.dogTypeRecyclerView.visibility = View.GONE
            binding.errorView.root.visibility = View.VISIBLE
        }
    }
}
