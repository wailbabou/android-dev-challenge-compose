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
package com.ouail.puppies

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.repositories.PuppiesRepository
import com.example.androiddevchallenge.views.DetailView
import com.example.androiddevchallenge.views.HomeView

@ExperimentalAnimationApi
@Composable
fun PuppiesApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeView(
                puppies = PuppiesRepository.instance.getPuppiesList(),
                navController = navController
            )
        }
        composable("kk") {
            HomeView(
                puppies = PuppiesRepository.instance.getPuppiesList(),
                navController = navController
            )
        }
        composable(
            "detail/{item}",
            arguments = listOf(
                navArgument("item") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            DetailView(
                navController = navController,
                puppy = PuppiesRepository.instance.getSinglePuppy(
                    backStackEntry.arguments?.getString("item")!!
                )
            )
        }
    }
}
