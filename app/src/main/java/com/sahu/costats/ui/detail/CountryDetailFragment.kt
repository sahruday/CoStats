package com.sahu.costats.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sahu.appUtil.ui.BaseFragment
import com.sahu.costats.R
import com.sahu.costats.data.cache.dao.HistoricStats
import com.sahu.costats.data.cache.dao.ProvinceStats
import com.sahu.costats.databinding.CountryDetailFragmentBinding
import com.sahu.costats.ui.detail.adapter.PastDaysStatsAdapter
import com.sahu.costats.ui.detail.adapter.StateListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CountryDetailFragment(private val country: String) :BaseFragment<CountryDetailFragmentBinding>(R.layout.country_detail_fragment) {

    companion object {
        fun newInstance(country: String) = CountryDetailFragment(country)
    }

    private val viewModel: CountryDetailViewModel by viewModels()

    private lateinit var binding: CountryDetailFragmentBinding

    private val stateAdapter = StateListAdapter ()
    private val historicAdapter = PastDaysStatsAdapter()

    override fun init(savedInstanceState: Bundle?, binding: CountryDetailFragmentBinding) {
        this.binding = binding
        setHasOptionsMenu(true)
        if(savedInstanceState == null)
            viewModel.country = country

        lifecycleScope.launchWhenResumed {
            viewModel.getProvinceData().collect {
                renderProvinceData(it)
            }

            viewModel.getPastData().collect {
                renderHistoricData(it)
            }
        }
        binding.provinceData.adapter = stateAdapter
        binding.pastData.adapter = historicAdapter
    }


    private fun renderHistoricData(list: List<HistoricStats>) {
        binding.pastDataTitle.visibility = if(list.isEmpty()) View.GONE else View.VISIBLE
        historicAdapter.submitList(list)
        historicAdapter.notifyDataSetChanged()
    }

    private fun renderProvinceData(list: List<ProvinceStats>) {
        binding.provinceDataTitle.visibility = if(list.isEmpty()) View.GONE else View.VISIBLE
        stateAdapter.submitList(list)
        stateAdapter.notifyDataSetChanged()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchMenuItem: android.view.MenuItem? = menu.findItem(R.id.action_search)
        searchMenuItem?.isVisible = false
    }

}