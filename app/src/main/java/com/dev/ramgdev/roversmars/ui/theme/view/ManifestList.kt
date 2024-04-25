package com.dev.ramgdev.roversmars.ui.theme.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.ramgdev.roversmars.R
import com.dev.ramgdev.roversmars.domain.model.RoverManifestUiModel

@Composable
fun ManifestList(
    modifier: Modifier,
    roverManifestUiModelList: List<RoverManifestUiModel>,
    roverName: String,
    onClick: (roverName: String, sol: String) -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(count = roverManifestUiModelList.size, itemContent = { index ->
                Manifest(
                    roverManifestUiModel = roverManifestUiModelList[index],
                    roverName,
                    onClick
                )
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ManifestPreview() {
    Manifest(
        roverManifestUiModel = RoverManifestUiModel(
            sol = "4",
            earthDate = "2021-2-12",
            photoNumber = "51"
        ),
        roverName = "",
        onClick = {_, _ ->}
    )
}

@Composable
fun Manifest(
    roverManifestUiModel: RoverManifestUiModel,
    roverName: String,
    onClick: (roverName: String, sol: String) -> Unit
) {
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .clickable {
            onClick(roverName, roverManifestUiModel.sol)
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.sol, roverManifestUiModel.sol))
            Text(text = stringResource(id = R.string.earth_date, roverManifestUiModel.earthDate))
            Text(
                text = stringResource(
                    id = R.string.photo_number,
                    roverManifestUiModel.photoNumber
                )
            )
        }
    }
}