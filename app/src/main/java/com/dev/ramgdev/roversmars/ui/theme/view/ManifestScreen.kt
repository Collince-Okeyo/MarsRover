package com.dev.ramgdev.roversmars.ui.theme.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dev.ramgdev.roversmars.domain.model.RoverManifestUiState
import com.dev.ramgdev.roversmars.ui.theme.manifest.MarsRoverManifestViewModel

@Preview
@Composable
fun ManifestScreenPreview() {

}

@Composable
fun ManifestScreen(
    roverName: String?,
    marsRoverManifestViewModel: MarsRoverManifestViewModel,
    onClick: (roverName: String, sol: String) -> Unit
) {
    val viewState by marsRoverManifestViewModel.roverManifestUiState.collectAsStateWithLifecycle()
    if (roverName != null) {
        LaunchedEffect(Unit) {
            marsRoverManifestViewModel.getMarsRoverManifest(roverName)
        }
        when (val roverManifestUiState = viewState) {
            RoverManifestUiState.Error -> Error()
            RoverManifestUiState.Loading -> Loading()
            is RoverManifestUiState.Success -> ManifestList(
                roverManifestUiModelList = roverManifestUiState.roverManifestUiModelList,
                roverName = roverName,
                onClick = onClick
            )
        }
    }
}
