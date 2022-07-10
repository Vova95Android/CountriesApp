package com.example.appxml.ui.countryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.appxml.base.BaseFragment
import com.example.appxml.databinding.FragmentCountryDetailBinding
import com.example.appxml.di.appComponent
import com.example.appxml.ui.activity.MainViewModel
import javax.inject.Inject

class CountryDetailFragment : BaseFragment<FragmentCountryDetailBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: CountryDetailViewModel by viewModels { factory }

    private val activityViewModel: MainViewModel by activityViewModels { factory }

    private val adapter = CountryDataAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireContext().applicationContext.appComponent.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dataList.adapter = adapter
    }

    override fun subscribeOnViewModel() {
        viewModel.countryDetail.subscribe {
            it?.let { data ->
                adapter.submitList(data.listData)
                binding.name.text = data.name
                binding.image.text = data.emoji
            }
        }
        activityViewModel.activeCountry.subscribe {
            it?.let { viewModel.selectCountry(it.code) }
        }
    }
}