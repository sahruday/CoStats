package com.sahu.costats.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahu.appUtil.Callback
import com.sahu.costats.data.Repository
import com.sahu.costats.data.cache.dao.CountryStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo : Repository
) : ViewModel() {

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

    var searchString: MutableLiveData<String> = MutableLiveData("")
    var data = arrayListOf<CountryStats>()

    fun getLocalData() = repo.getData()

    fun fetchData() = viewModelScope.launch(Dispatchers.IO) {
        repo.getDataFromApi().collect {
            when(it){
                is Callback.Error -> {

                }
                Callback.Success -> {

                }
            }
        }
    }

    fun fetchProvinceData() = viewModelScope.launch(Dispatchers.IO) {
        repo.getProvinceDataFromApi().collect()
    }

    fun fetchHistoricData() = viewModelScope.launch(Dispatchers.IO) {
        repo.getHistoricDataFromApi().collect()
    }
}