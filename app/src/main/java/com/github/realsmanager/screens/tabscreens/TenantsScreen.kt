package com.github.realsmanager.screens.tabscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.realsmanager.R

@Composable
fun TenantsScreen(
    modifier: Modifier = Modifier,
    tenants: List<TenantModel>
) {
    LazyVerticalGrid(
        modifier = modifier.scale(1.01f),
        columns = GridCells.Adaptive(500.dp),
        content = {
            items(tenants.size) {
                TenantCard(tenantModel = tenants[it])
            }
        }
    )
}

@Composable
fun TenantCard(
    modifier: Modifier = Modifier,
    tenantModel: TenantModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
                ){
            // user profile
            Surface(
                modifier = Modifier
                    .weight(.6f)
                    .padding(all = 2.dp)
                    .size(width = 100.dp, height = 140.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Image(
                    painter = painterResource(id = tenantModel.profilePhoto),
                    contentDescription = tenantModel.name,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 300f,
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = tenantModel.toString(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.weight(.05f))
            Divider(
                modifier = Modifier
                    .width(2.dp)
                    .height(60.dp)
            )
            Spacer(modifier = Modifier.weight(.05f))
            // details
            Column(
                modifier = Modifier
                    .weight(.4f)
                    .padding(2.dp)
            ) {
                Row {
                    Text(text = "Rent: ")
                    Text(text = tenantModel.rentDue.toString())
                }
                Row {
                    Text(text = "Utilities: ")
                    Text(text = tenantModel.utilityPaymentDue.toString())
                }
            }
        }
    }
}

data class TenantModel(
    val profilePhoto: Int = R.drawable.baseline_person_24,
    val name: String,
    val sex: String,
    val age: Int = 0,
    val buildingName: String,
    val roomNo: String,
    val rentDue: Int = 0,
    val utilityPaymentDue: Int = 0
) {
    override fun toString(): String {
        return "$name , $age , $sex\n" +
                "$buildingName ,  $roomNo";
    }
}

@Preview
@Composable
fun TenantsScreenPreview() {
    val tenantModel = TenantModel(
        profilePhoto = R.drawable.kmm,
        buildingName = "Divine Mercy",
        roomNo = "F12",
        age = 20,
        name = "P. Prometheus",
        sex = "F"
    )
    TenantCard(tenantModel = tenantModel)
}