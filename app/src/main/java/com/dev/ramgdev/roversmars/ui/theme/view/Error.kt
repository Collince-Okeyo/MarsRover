package com.dev.ramgdev.roversmars.ui.theme.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dev.ramgdev.roversmars.R

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    Error()
}


@Composable
fun Error() {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.error), style = MaterialTheme.typography.bodyLarge)
    }
}