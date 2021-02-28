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
package com.example.androiddevchallenge.repositories

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.models.Puppy
import java.util.ArrayList

class PuppiesRepository {
    companion object {
        val instance = PuppiesRepository()
        private val data = arrayListOf(
            Puppy("Joy", "Labrador Retriever", "12KM", R.drawable.p1),
            Puppy("Milly", "German Shepherd", "13KM", R.drawable.p2),
            Puppy("Swinzy", "Golden Retriever", "14KM", R.drawable.p3),
            Puppy("Pizaro", "Poodle", "15KM", R.drawable.p4),
            Puppy("Bob", "Bulldog", "16KM", R.drawable.p5),
            Puppy("Flofy", "Poodle", "12KM", R.drawable.p1),
            Puppy("Hope", "Golden Retriever", "13KM", R.drawable.p2),
            Puppy("Smay", "Golden Retriever", "14KM", R.drawable.p3),
            Puppy("Brok", "Bulldog", "15KM", R.drawable.p4),
            Puppy("Timy", "Bulldog", "16KM", R.drawable.p5)
        )
    }

    fun getPuppiesList(): ArrayList<Puppy> {
        return data
    }
    fun getSinglePuppy(puppyName: String): Puppy {
        return data.first { it.name == puppyName }
    }
}
