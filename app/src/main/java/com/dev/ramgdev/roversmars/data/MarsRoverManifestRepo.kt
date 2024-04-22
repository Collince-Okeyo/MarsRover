package com.dev.ramgdev.roversmars.data

import com.dev.ramgdev.roversmars.domain.model.RoverManifestUiState
import com.dev.ramgdev.roversmars.domain.model.toUiModel
import com.dev.ramgdev.roversmars.service.MarsRoverManifestService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarsRoverManifestRepo @Inject constructor(
    private val roverManifestService: MarsRoverManifestService
) {
    fun getMarsRoverManifest(roverName: String): Flow<RoverManifestUiState> = flow {
        try {
            emit(
                toUiModel(
                    roverManifestService.getMarsRoverManifest(
                        roverName.lowercase()
                    )
                )
            )
        } catch (throwable: Throwable) {
            emit(RoverManifestUiState.Error)
        }
    }
}