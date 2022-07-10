package com.example.appxml.ui.countryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.appxml.base.BaseFragment
import com.example.appxml.databinding.FragmentCountryListBinding
import com.example.appxml.di.appComponent
import com.example.appxml.ui.activity.MainViewModel
import javax.inject.Inject

class CountryListFragment : BaseFragment<FragmentCountryListBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: CountryListViewModel by viewModels { factory }

    private val activityViewModel: MainViewModel by activityViewModels { factory }

    private val adapter = CountryListAdapter {
        activityViewModel.selectCountry(it)
        viewModel.selectCountry(it.code)
    }

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
        with(binding) {
            list.adapter = adapter
            search.doOnTextChanged { text, _, _, _ ->
                viewModel.setQuery(text?.toString())
            }
        }
    }

    override fun subscribeOnViewModel() {
        viewModel.countryList.subscribe {
            adapter.submitList(it)
        }
    }
}