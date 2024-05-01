package com.dev.ramgdev.roversmars.ui.theme.view

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.ramgdev.roversmars.domain.model.roverUiModelList

@Composable
fun RoverList(
    modifier: Modifier,
    onclick: (roverName: String) -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(count = roverUiModelList.size, itemContent = { index ->
                Rover(
                    name = roverUiModelList[index].name,
                    img = roverUiModelList[index].image,
                    landingDate = roverUiModelList[index].landingDate,
                    distanceTravelled = roverUiModelList[index].distance,
                    onclick = onclick
                )
            })
        }
    }
}

@Preview
@Composable
fun RoverPreview() {
//    RoverList() {}
}

@Composable
fun Rover(
    name: String,
    img: Int,
    landingDate: String,
    distanceTravelled: String,
    onclick: (roverName: String) -> Unit
) {
    Card(modifier = Modifier
        .padding(16.dp)
        .clickable { onclick(name) }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
            Image(painter = painterResource(id = img), contentDescription = null)
            Text(text = "Credit: NASA/JPL", style = MaterialTheme.typography.labelSmall)
            Text(text = "Landing date: $landingDate", style = MaterialTheme.typography.bodySmall)
            Text(
                text = "Distance travel: $distanceTravelled",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}