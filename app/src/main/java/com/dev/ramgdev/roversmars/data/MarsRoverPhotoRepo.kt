package com.dev.ramgdev.roversmars.data

import com.dev.ramgdev.roversmars.domain.model.RoverPhotoUiModel
import com.dev.ramgdev.roversmars.domain.model.RoverPhotoUiState
import com.dev.ramgdev.roversmars.service.MarsRoverPhotoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarsRoverPhotoRepo @Inject constructor(
    private val marsRoverPhotoService: MarsRoverPhotoService
) {
    fun getMarsRoverPhoto(roverName: String, sol: String): Flow<RoverPhotoUiState> = flow {
        try {
            val networkResult = marsRoverPhotoService.getMarsRoverPhotos(roverName, sol)
            emit(
                RoverPhotoUiState.Success(networkResult.photos.map { photo ->
                    RoverPhotoUiModel(
                        id = photo.id,
                        roverName = photo.rover.name,
                        imgSrc = photo.imgSrc,
                        sol = photo.sol.toString(),
                        earthDate = photo.earthDate,
                        cameraFullName = photo.camera.fullName
                    )
                })
            )
        } catch (throwable: Throwable) {
            emit(RoverPhotoUiState.Error)
        }
    }
}