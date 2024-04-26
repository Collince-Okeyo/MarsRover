package com.dev.ramgdev.roversmars.domain.model

import com.dev.ramgdev.roversmars.db.MarsRoverSavedLocalModel

fun toDbModel(roverPhotoUiModel: RoverPhotoUiModel): MarsRoverSavedLocalModel =
    MarsRoverSavedLocalModel(
        roverPhotoId = roverPhotoUiModel.id,
        roverName = roverPhotoUiModel.roverName,
        imgSrc = roverPhotoUiModel.imgSrc,
        sol = roverPhotoUiModel.sol,
        earthDate = roverPhotoUiModel.earthDate,
        cameraFullName = roverPhotoUiModel.cameraFullName
    )

fun toUiModel(marsRoverSavedLocalModelList: List<MarsRoverSavedLocalModel>) =
    marsRoverSavedLocalModelList.map { photo ->
        RoverPhotoUiModel(
            id = photo.roverPhotoId,
            roverName = photo.roverName,
            imgSrc = photo.imgSrc,
            sol = photo.sol,
            earthDate = photo.earthDate,
            cameraFullName = photo.cameraFullName,
            isSaved = true
        )
    }