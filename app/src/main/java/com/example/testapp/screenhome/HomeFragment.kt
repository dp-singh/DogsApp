package com.example.testapp.screenhome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.domain.model.Resource
import com.example.domain.model.TopEmployeeWithSalary200
import com.example.testapp.R
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_main) {
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.viewState.observe(viewLifecycleOwner) {
            handleViewState(it)
        }
        retryButton.setOnClickListener {
            homeViewModel.onViewEvent(HomeViewEvents.Refresh)
        }
    }

    private fun handleViewState(result: Resource<TopEmployeeWithSalary200>) = when (result) {
        Resource.Loading -> {
            topEmployeeTextView.setText(R.string.loading_text)
            restEmployeeTextView.setText(R.string.loading_text)
        }
        is Resource.Success -> {
            topEmployeeTextView.text =
                getString(R.string.text_first_employee_200_above, result.data.topEmployee)
            restEmployeeTextView.text =
                getString(R.string.text_total_employee_200_above, result.data.restEmployeeCount)
        }
        is Resource.Error -> {
            topEmployeeTextView.text = ""
            restEmployeeTextView.text = result.throwable.localizedMessage
        }
    }
}
