package com.example.lesson_10_rudomanov.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_10_rudomanov.data.ApiClient
import com.example.lesson_10_rudomanov.data.model.Bridge
import kotlinx.coroutines.launch

class MapViewModel : ViewModel() {

    private val _listLiveData = MutableLiveData<LoadState<List<Bridge>?>>()
    val listLiveData: LiveData<LoadState<List<Bridge>?>> = _listLiveData

    fun mapJob() {
        viewModelScope.launch {
            _listLiveData.postValue(LoadState.Loading())
            try {
                val bridges = ApiClient.apiService.getBridges()
                _listLiveData.postValue(LoadState.Data(bridges))
            } catch (e: Exception) {
                _listLiveData.postValue(LoadState.Error(e))
            }
        }
    }
}