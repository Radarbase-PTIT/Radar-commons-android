/*
 * Copyright 2017 The Hyve
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.radarbase.passive.weather

import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.Keep
import org.radarbase.android.BuildConfig
import org.radarbase.android.RadarService
import org.radarbase.android.source.BaseSourceState
import org.radarbase.android.source.SourceProvider

@Keep
open class WeatherApiProvider(radarService: RadarService) : SourceProvider<BaseSourceState>(radarService) {
    override val description: String?
        get() = radarService.getString(R.string.weather_api_description)

    override val pluginNames = listOf(
            "weather",
            "weather_api",
            ".weather.WeatherApiProvider",
            "org.radarbase.passive.weather.WeatherApiProvider",
            "org.radarcns.weather.WeatherApiProvider")

    override val serviceClass: Class<WeatherApiService> = WeatherApiService::class.java

    override val displayName: String
        get() = radarService.getString(R.string.weatherApiServiceDisplayName)

    override val permissionsNeeded: List<String> = buildList(3) {
        add(ACCESS_COARSE_LOCATION)
        add(ACCESS_FINE_LOCATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            add(ACCESS_BACKGROUND_LOCATION)
        }
    }

    override val featuresNeeded: List<String> = listOf(PackageManager.FEATURE_LOCATION)

    override val sourceProducer: String = "OpenWeatherMap"

    override val sourceModel: String = "API"

    override val version: String = BuildConfig.VERSION_NAME

    override val isDisplayable: Boolean = false
}
