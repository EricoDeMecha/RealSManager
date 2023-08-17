package com.github.realsmanager.screens.tabscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.realsmanager.R


@Composable
fun BuildingsScreen(
    modifier: Modifier = Modifier,
    buildings: List<BuildingModel>
) {
    LazyVerticalGrid(
        modifier = modifier.scale(1.01f),
        columns = GridCells.Adaptive(400.dp),
        content = {
            items(buildings.size) {
                BuildingCard(buildingModel = buildings[it])
            }
        }
    )
}


@Composable
fun BuildingCard(
    modifier: Modifier = Modifier,
    buildingModel: BuildingModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 2.dp)
            .clickable {  },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.7f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildingModel.name,
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = buildingModel.nWorkers.toString(),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Workers",
                                fontSize = 16.sp,
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = buildingModel.nTenants.toString(),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Tenants",
                                fontSize = 16.sp,
                            )
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .height(60.dp)
                            .width(1.dp)
                    )
                    // model unit count
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = buildingModel.nOccupied.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(text = "out of")
                        Text(
                            text = buildingModel.nCapacity.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "Units",
                            style = TextStyle(
                                fontSize = 15.sp
                            )
                        )
                    }
                }
            }
            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(all = 5.dp)
                    .size(width = 100.dp, height = 140.dp)
            ) {
                Image(
                    painter = buildingModel.painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = buildingModel.name
                )
            }

        }
    }
}

data class BuildingModel(
    val name: String,
    val painter: Painter,
    val nCapacity: Int,
    val nOccupied: Int,
    val nWorkers: Int,
    val nTenants: Int
)

@Preview
@Composable
fun BuildingsScreenPreview() {
    val buildingModel = BuildingModel(
        "Building1",
        painterResource(id = R.drawable.building5),
        600,
        300,
        24,
        300
    )
    BuildingCard(buildingModel = buildingModel)
}