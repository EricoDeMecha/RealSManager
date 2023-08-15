package com.github.realsmanager.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.realsmanager.R
import com.github.realsmanager.models.HomeTabModel
import com.github.realsmanager.screens.tabscreens.BuildingsScreen
import com.github.realsmanager.screens.tabscreens.TenantsScreen
import com.github.realsmanager.screens.tabscreens.WorkersScreen
import kotlinx.coroutines.launch

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
            HomeTabs(
                modifier = Modifier.fillMaxWidth()
            )
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabs(
    modifier: Modifier  = Modifier
) {
    val tabList = listOf(
        HomeTabModel("Buildings"),
        HomeTabModel("Workers"),
        HomeTabModel("Tenants"),
    )

    val pagerState = rememberPagerState { tabList.size } // page count
    val scope = rememberCoroutineScope()

    Column{
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabList.forEachIndexed { index, item ->
                HomeTabItem(
                    modifier = modifier,
                    onClick = { scope.launch { pagerState.scrollToPage(index) } },
                    selected = pagerState.currentPage == index,
                    title = item.title,
                    icon = item.icon
                )
            }
            HorizontalPager(state = pagerState) { currentPage ->
                when (currentPage) {
                    0 -> {
                        BuildingsScreen()
                    }

                    1 -> {
                        WorkersScreen()
                    }

                    2 -> {
                        TenantsScreen()
                    }
                }
            }
        }
    }

}

@Composable
fun HomeTabItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    selected: Boolean,
    title: String,
    icon: ImageVector? = null
) {
    val selectedColor =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

    Tab(
        selected = selected,
        onClick = onClick
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (icon != null) Icon(icon, contentDescription = title, tint = selectedColor)
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = title,
                color = selectedColor
            )
        }
    }
}

@Preview
@Composable
fun LandingPagePreview() {
    LandingPage()
}