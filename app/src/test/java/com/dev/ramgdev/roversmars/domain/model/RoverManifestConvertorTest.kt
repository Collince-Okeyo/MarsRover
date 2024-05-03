package com.dev.ramgdev.roversmars.domain.model

import com.dev.ramgdev.roversmars.domain.model.RoverManifestUiModel
import com.dev.ramgdev.roversmars.domain.model.RoverManifestUiState
import com.dev.ramgdev.roversmars.domain.model.toUiModel
import com.dev.ramgdev.roversmars.service.model.ManifestPhotoRemoteModel
import com.dev.ramgdev.roversmars.service.model.PhotoManifestRemoteModel
import com.dev.ramgdev.roversmars.service.model.RoverManifestRemoteModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class RoverManifestConvertorTest {
    @Test
    fun `should convert roverManifestRemoteModel to RoverManifestUiState`() {
        // Given
        val roverManifestRemoteModel = RoverManifestRemoteModel(
            photoManifest = PhotoManifestRemoteModel(
                landingDate = "2021-02.18",
                launchDate = "2020-07-30",
                maxDate = "2023-05-19",
                maxSol = "799",
                name = "Perseverance",
                photos = listOf(
                    ManifestPhotoRemoteModel(
                        cameras = listOf("REAR_HAZCAM_LEFT", "REAR_HAZCAM_RIGHT"),
                        earthDate = "2021-02-18",
                        sol = 0,
                        totalPhotos = 54
                    ),
                    ManifestPhotoRemoteModel(
                        cameras = listOf("EDL_DDCAM", "EDL_PUCAM2"),
                        earthDate = "2021-02-19",
                        sol = 1,
                        totalPhotos = 201
                    )
                ),
                status = "active",
                totalPhotos = 156687
            )
        )
        // When
        val result = toUiModel(roverManifestRemoteModel)
        // Then
        val expectedResult = RoverManifestUiState.Success(
            listOf(
                RoverManifestUiModel(
                    sol = "1",
                    earthDate = "2021-02-19",
                    photoNumber = "201"
                ),
                RoverManifestUiModel(
                    sol = "0",
                    earthDate = "2021-02-18",
                    photoNumber = "54"
                ),

            )
        )
        assertEquals(expectedResult, result)
    }
}