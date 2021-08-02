package com.sahu.costats.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sahu.appUtil.ui.BaseFragment
import com.sahu.costats.R
import com.sahu.costats.data.cache.dao.CountryStats
import com.sahu.costats.databinding.MainFragmentBinding
import com.sahu.costats.ext.addFragment
import com.sahu.costats.ui.detail.CountryDetailFragment
import com.sahu.costats.ui.main.adapter.CountryStatsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding>(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels() //to get same view model else where.

    private lateinit var binding: MainFragmentBinding

    private val adapter = CountryStatsAdapter { countryStats, sharedElements ->
        val fragment = CountryDetailFragment.newInstance(countryStats.country)

        addFragment(R.id.container,
            fragment,
            "COUNTRY_DETAIL",
            sharedElements,
            true
        )
    }

    override fun init(savedInstanceState: Bundle?, binding: MainFragmentBinding) {
        this.binding = binding
        setHasOptionsMenu(true)
        lifecycleScope.launchWhenResumed {
            viewModel.getLocalData().collect { it ->
                viewModel.data = ArrayList(it)
                render(it)
            }
        }
        viewModel.searchString.observe(viewLifecycleOwner, {searchString ->
            if(searchString.isNullOrBlank())
                render(viewModel.data)
            else
                render(viewModel.data.filter { it.country.contains(searchString, true) })
        })
        if(savedInstanceState == null) { //call only on initial setup.
            viewModel.fetchData()
            viewModel.fetchProvinceData()
            viewModel.fetchHistoricData()
        }
        binding.cycle.adapter = adapter
    }

    private fun render(list: List<CountryStats>){
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(requireContext(), SearchManager::class.java) as SearchManager
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchString.postValue(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchString.postValue(newText)
                return true
            }

        })

        return super.onCreateOptionsMenu(menu, inflater)
    }

}