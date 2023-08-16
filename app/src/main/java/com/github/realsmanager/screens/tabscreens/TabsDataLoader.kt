package com.github.realsmanager.screens.tabscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.realsmanager.R

@Composable
fun TabsDataLoader(
    images: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier.scale(1.01f),
        columns = GridCells.Fixed(3),
        content = {
            items(images.size) {
                Image(
                    painter = images[it],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .border(
                            width = 1.dp,
                            color = Color.White
                        )
                )
            }
        }
    )
}

@Composable
fun BuildingCard(
    modifier: Modifier = Modifier
) {
    val isSelected by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Text(
            text = "Building 1"
        )
        Image(
            painter = painterResource(id = R.drawable.kmm),
            contentDescription = null
        )
        Row{
            Column {
                Text(text = "2")
                Text(text = "Workers")
            }
            Column {
                Text(text = "24")
                Text(text = "Tenants")
            }
        }
    }

}

@Preview
@Composable
fun BuildingCardPreview() {
    BuildingCard()
}