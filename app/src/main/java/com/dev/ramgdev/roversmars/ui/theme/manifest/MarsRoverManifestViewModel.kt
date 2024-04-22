package com.dev.ramgdev.roversmars.ui.theme.manifest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.ramgdev.roversmars.data.MarsRoverManifestRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarsRoverManifestViewModel @Inject constructor(
    val marsRoverManifestRepo: MarsRoverManifestRepo
): ViewModel() {
    fun getMarsRoverManifest(roverName: String) {
        viewModelScope.launch {
            marsRoverManifestRepo.getMarsRoverManifest(roverName).collect {

            }
        }
    }
}