package com.github.realsmanager.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.realsmanager.R
import com.github.realsmanager.models.HomeTabModel
import com.github.realsmanager.screens.tabscreens.BuildingModel
import com.github.realsmanager.screens.tabscreens.BuildingsScreen
import com.github.realsmanager.screens.tabscreens.TenantModel
import com.github.realsmanager.screens.tabscreens.TenantsScreen
import com.github.realsmanager.screens.tabscreens.WorkersScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage() {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    val searchItemList = remember {
        mutableListOf("")
    }
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val homeTabModels = listOf(
        HomeTabModel(
            icon = painterResource(id = R.drawable.baseline_house_24),
            title = "Buildings"
        ),
        HomeTabModel(
            icon = painterResource(id = R.drawable.baseline_engineering_24),
            title = "Workers"
        ),
        HomeTabModel(
            icon = painterResource(id = R.drawable.baseline_people_24),
            title = "Tenants"
        )
    )

    Scaffold {
        Column(modifier = Modifier.fillMaxSize()) {
            //search bar
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = text,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    active = false
                    searchItemList.add(it)
                    text = ""
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text("Search")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (text.isNotEmpty()) {
                                    text = ""
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            ) {
                searchItemList.forEach {
                    Row(modifier = Modifier.padding(all = 14.dp)) {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            imageVector = Icons.Default.History,
                            contentDescription = "History"
                        )
                        Text(text = it)
                    }
                }
            }
            // profile bar
            ProfileBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Spacer(Modifier.height(10.dp))
            HomeTabs(
                homeTabModels = homeTabModels,
                onTabSelected = {
                    selectedTabIndex = it
                }
            )
            when (selectedTabIndex) {
                0 -> BuildingsScreen(
                    buildings = listOf(
                        BuildingModel(
                            "Building 1",
                            painterResource(id = R.drawable.building1),
                            300,
                            300,
                            30,
                            300
                        ),
                        BuildingModel(
                            "Building 2",
                            painterResource(id = R.drawable.building2),
                            600,
                            300,
                            26,
                            300
                        ),
                        BuildingModel(
                            "Building 3",
                            painterResource(id = R.drawable.building3),
                            700,
                            300,
                            24,
                            300
                        ),
                        BuildingModel(
                            "Building 4",
                            painterResource(id = R.drawable.building5),
                            700,
                            300,
                            24,
                            300
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 5.dp)
                )

                1 -> WorkersScreen(
                    images = listOf(
                        painterResource(id = R.drawable.worker1),
                        painterResource(id = R.drawable.worker2),
                        painterResource(id = R.drawable.worker4),
                        painterResource(id = R.drawable.worker1),
                        painterResource(id = R.drawable.worker2),
                        painterResource(id = R.drawable.worker1),
                        painterResource(id = R.drawable.worker2),
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                2 -> TenantsScreen(
                    tenants = listOf(
                        TenantModel(
                            profilePhoto = R.drawable.user1,
                            buildingName = "Divine Mercy",
                            roomNo = "F12",
                            age = 20,
                            name = "P. Prometheus",
                            sex = "F"
                        ),
                        TenantModel(
                            profilePhoto = R.drawable.user2,
                            buildingName = "Divine Mercy",
                            roomNo = "F12",
                            age = 20,
                            name = "P. Prometheus",
                            sex = "F"
                        ),
                        TenantModel(
                            profilePhoto = R.drawable.user3,
                            buildingName = "Divine Mercy",
                            roomNo = "F12",
                            age = 20,
                            name = "P. Prometheus",
                            sex = "F"
                        ),
                        TenantModel(
                            profilePhoto = R.drawable.user4,
                            buildingName = "Divine Mercy",
                            roomNo = "F12",
                            age = 20,
                            name = "P. Prometheus",
                            sex = "F"
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun ProfileBar(
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.owner_profile),
                contentDescription = "Owner profile"
            )
            Text(
                text = "Mohammed Hussein",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "2",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Buildings",
                fontSize = 16.sp,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "24",
                fontSize = 32.sp,
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
                text = "300",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Tenants",
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun HomeTabs(
    modifier: Modifier = Modifier,
    homeTabModels: List<HomeTabModel>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    var inactiveColor = Color(0xFF777777)

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        homeTabModels.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Icon(
                    painter = item.icon,
                    contentDescription = item.title,
                    tint = if (selectedTabIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun LandingPagePreview() {
    LandingPage()
}