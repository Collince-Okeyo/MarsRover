package com.dev.ramgdev.roversmars.ui.theme.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dev.ramgdev.roversmars.R
import com.dev.ramgdev.roversmars.domain.model.RoverPhotoUiModel

@Composable
fun PhotoList(
    modifier: Modifier,
    roverPhotoUiModelList: List<RoverPhotoUiModel>,
    onClick: (roverPhotoUiModel: RoverPhotoUiModel) -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(count = roverPhotoUiModelList.size, itemContent = { index ->
                Photo(roverPhotoUiModel = roverPhotoUiModelList[index], onClick)
//                Timber.d("Clicked $onClick")
            })
        }
    }
}

@Composable
fun Photo(
    roverPhotoUiModel: RoverPhotoUiModel,
    onClick: (roverPhotoUiModel: RoverPhotoUiModel) -> Unit
) {
    Card(modifier = Modifier
        .padding(16.dp)
        .clickable {
            onClick(roverPhotoUiModel)
        }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {

                Image(
                    painter = painterResource(
                        id = if (roverPhotoUiModel.isSaved) {
                            R.drawable.ic_save
                        } else {
                            R.drawable.ic_outline_save
                        }
                    ), contentDescription = "save icon"
                )

                Text(
                    text = roverPhotoUiModel.roverName,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            AsyncImage(
                model = roverPhotoUiModel.imgSrc,
                contentDescription = "photo image",
                modifier = Modifier.height(300.dp)
            )

            Text(
                text = stringResource(id = R.string.sol, roverPhotoUiModel.sol),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(id = R.string.earth_date, roverPhotoUiModel.earthDate),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = roverPhotoUiModel.cameraFullName,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoPreview() {
    Photo(
        roverPhotoUiModel = RoverPhotoUiModel(
            id = 4,
            roverName = "Curiosity",
            imgSrc = "https//domain.com",
            sol = "34",
            earthDate = "2024-03-23",
            cameraFullName = "Mast Camera Zoom- Right",
            true,
        )
    ){}
}