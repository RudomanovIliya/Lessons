package com.example.lesson_12_rudomanov.presentation.bridges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_12_rudomanov.data.remote.repository.BridgesRepository
import com.example.lesson_12_rudomanov.presentation.LoadState
import com.example.lesson_12_rudomanov.presentation.bridges.model.Bridge
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoBridgeViewModel @Inject constructor(
    private val repository: BridgesRepository,
) : ViewModel() {

    private val _bridgeLiveData = MutableLiveData<LoadState<Bridge>>()
    val bridgeLiveData: LiveData<LoadState<Bridge>> = _bridgeLiveData

    fun loadBridge(id: Int) {
        viewModelScope.launch {
            _bridgeLiveData.postValue(LoadState.Loading())
            try {
                val bridge = repository.getBridge(id)
                _bridgeLiveData.postValue(LoadState.Data(bridge))
            } catch (e: Exception) {
                _bridgeLiveData.postValue(LoadState.Error(e))
            }
        }
    }
}