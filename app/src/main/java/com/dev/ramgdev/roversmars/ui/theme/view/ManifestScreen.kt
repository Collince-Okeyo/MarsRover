package com.dev.ramgdev.roversmars.ui.theme.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ManifestScreen(
    roverName: String?
) {
    Text(text = "Manifest Screen $roverName")
}

@Preview
@Composable
fun ManifestScreenPreview() {
    ManifestScreen("Perseverence")
}