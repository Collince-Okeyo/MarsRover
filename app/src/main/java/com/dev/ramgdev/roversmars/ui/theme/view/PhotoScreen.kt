package com.dev.ramgdev.roversmars.ui.theme.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dev.ramgdev.roversmars.domain.model.RoverPhotoUiState
import com.dev.ramgdev.roversmars.ui.theme.photolist.MarsRoverPhotoViewModel


@Preview
@Composable
fun PhotoScreenPreview() {
//    PhotoScreen()
}
@Composable
fun PhotoScreen(
    roverName: String?,
    sol: String?,
    marsRoverPhotoViewModel: MarsRoverPhotoViewModel,
) {
    val viewState by marsRoverPhotoViewModel.roverPhotoUiState.collectAsStateWithLifecycle()

    if (roverName != null && sol != null) {
        LaunchedEffect(Unit) {
            marsRoverPhotoViewModel.getMarsRoverPhoto(roverName, sol)
        }
    }
    when (val roverPhotoUiState = viewState) {
        RoverPhotoUiState.Error -> Error()
        RoverPhotoUiState.Loading -> Loading()
        is RoverPhotoUiState.Success -> PhotoList(
            roverPhotoUiModelList = roverPhotoUiState.roverPhotoUiModelList
        )
    }
}