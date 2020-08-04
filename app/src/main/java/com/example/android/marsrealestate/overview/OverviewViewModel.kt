/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MarsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {
    private val viewModelJob = Job ()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getMarsRealEstateProperties()
    }

    //Gets the result from request and updates the live data
    private fun getMarsRealEstateProperties() {
        coroutineScope.launch {
            var propertiesDeferredObj = MarsApi.retrofitService.getProperties()
            try {
                val propertiesList = propertiesDeferredObj.await()
                _response.value = "Found" + propertiesList?.size+ " record for Mars properties"

            } catch (e: Exception) {
                _response.value = "Request Failed " + e.message
            }
            println("I am everywhere")
        }
    }
}
