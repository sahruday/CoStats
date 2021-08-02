package com.sahu.costats.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahu.appUtil.Callback
import com.sahu.costats.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    var country: String = ""

    fun getPastData() = repo.getHistoricData(country)

    fun getProvinceData() = repo.getProvinceData(country)

    fun fetchProvinceData() =
        viewModelScope.launch(Dispatchers.IO) {
            repo.getProvinceDataFromApi().collect()
        }


}