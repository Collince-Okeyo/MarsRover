package com.dev.ramgdev.roversmars.data

import com.dev.ramgdev.roversmars.db.MarsRoverSavedPhotoDao
import com.dev.ramgdev.roversmars.domain.model.RoverPhotoUiModel
import com.dev.ramgdev.roversmars.domain.model.RoverPhotoUiState
import com.dev.ramgdev.roversmars.domain.model.toDbModel
import com.dev.ramgdev.roversmars.domain.model.toUiModel
import com.dev.ramgdev.roversmars.service.MarsRoverPhotoService
import com.dev.ramgdev.roversmars.service.model.RoverPhotoRemoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarsRoverPhotoRepo @Inject constructor(
    private val marsRoverPhotoService: MarsRoverPhotoService,
    private val marsRoverSavedPhotoDao: MarsRoverSavedPhotoDao
) {

    private fun getAllRemoteMarsRoverPhotos(
        roverName: String,
        sol: String
    ): Flow<RoverPhotoRemoteModel?> = flow {
        try {
            val networkResult = marsRoverPhotoService.getMarsRoverPhotos(
                roverName.lowercase(),
                sol
            )
            emit(networkResult)
        } catch (throwable: Throwable) {
            emit(null)
        }
    }
    /*fun getMarsRoverPhoto(roverName: String, sol: String): Flow<RoverPhotoUiState> = flow {
        *//*try {
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
        }*/

    fun getMarsRoverPhoto(roverName: String, sol: String): Flow<RoverPhotoUiState> =
        marsRoverSavedPhotoDao.allSavedIds(roverName, sol).combine(
            getAllRemoteMarsRoverPhotos(roverName, sol)
        ) { local, remote ->
            remote?.let { roverPhotoRemoteModel ->
                RoverPhotoUiState.Success(
                    roverPhotoRemoteModel.photos.map { photo ->
                        RoverPhotoUiModel(
                            id = photo.id,
                            roverName = photo.rover.name,
                            imgSrc = photo.imgSrc,
                            sol = photo.sol.toString(),
                            earthDate = photo.earthDate,
                            cameraFullName = photo.camera.fullName,
                            isSaved = local.contains(photo.id)
                        )
                    }
                )
            } ?: run {
                RoverPhotoUiState.Error
            }
        }

    suspend fun savePhoto(roverPhotoUiModel: RoverPhotoUiModel) {
        marsRoverSavedPhotoDao.insert(toDbModel(roverPhotoUiModel))
    }

    suspend fun removePhoto(roverPhotoUiModel: RoverPhotoUiModel) {
        marsRoverSavedPhotoDao.delete(toDbModel(roverPhotoUiModel))
    }

    fun getAllSaved(): Flow<RoverPhotoUiState> =
        marsRoverSavedPhotoDao.getAllSaved().map { localModel ->
            RoverPhotoUiState.Success(toUiModel(localModel))
        }
}


