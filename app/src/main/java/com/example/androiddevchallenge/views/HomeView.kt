/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.models.Puppy
import com.example.androiddevchallenge.repositories.PuppiesRepository
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun HomeView(
    puppies: List<Puppy>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.appName)) }
            )
        }
    ) { innerPadding ->
        PuppiesList(
            puppies = puppies,
            modifier = modifier.padding(innerPadding),
            onClick = {
                // handle on click
                navController.navigate("detail/${it.name}")
            }
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun PuppiesList(
    puppies: List<Puppy>,
    modifier: Modifier = Modifier,
    onClick: (item: Puppy) -> Unit
) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier) {
        LazyColumn(
            state = scrollState,
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 80.dp
            ),
        ) {
            items(puppies) { puppy ->
                PuppyListItem(
                    puppy = puppy,
                    onClick = onClick
                )
                Divider()
            }
        }
        val shownScroll = scrollState.firstVisibleItemScrollOffset > 0
        AnimatedVisibility(
            visible = shownScroll,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(
                onClick = {
                    coroutineScope.launch {
                        // Animate scroll to the first item
                        scrollState.animateScrollToItem(index = 0)
                    }
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun PuppyListItem(
    puppy: Puppy,
    onClick: (item: Puppy) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(
                onClick = {
                    onClick(puppy)
                }
            )
            .padding(vertical = 8.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PuppyItemImage(puppy)
            Spacer(Modifier.size(20.dp))
            PuppyItemContent(puppy)
        }
    }
}

@Composable
fun PuppyItemContent(puppy: Puppy) {
    Column {
        Text(
            text = puppy.name,
            style = MaterialTheme.typography.h6
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = puppy.type,
                style = MaterialTheme.typography.caption
            )
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.LocationOn,
                    null,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = puppy.distance,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun PuppyItemImage(puppy: Puppy) {
    Card(
        shape = CircleShape,
        elevation = 0.dp,
        modifier = Modifier
            .size(60.dp)
    ) {
        Image(
            painter = painterResource(id = puppy.imgRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
    }
}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(50),
        modifier = modifier
    ) {
        Text(text = stringResource(R.string.to_top_button_text))
    }
}
/*
@Preview("puppy item")
@Composable
fun previewPuppyItem() {
    PuppyListItem(puppy = PuppiesRepository.instance.getPuppiesList()[0])
}

@Preview("puppies list")
@Composable
fun previewPuppiesList(){
    PuppiesList(puppies = PuppiesRepository.instance.getPuppiesList())
}*/

@Preview("Home view")
@ExperimentalAnimationApi
@Composable
fun PreviewHome() {
    HomeView(
        puppies = PuppiesRepository.instance.getPuppiesList(),
        navController = rememberNavController()
    )
}
