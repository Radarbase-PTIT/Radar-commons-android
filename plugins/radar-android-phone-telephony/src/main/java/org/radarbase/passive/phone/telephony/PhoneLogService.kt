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

package org.radarbase.passive.phone.telephony

import org.radarbase.android.config.SingleRadarConfiguration
import org.radarbase.android.source.BaseSourceState
import org.radarbase.android.source.SourceManager
import org.radarbase.android.source.SourceService

import java.util.concurrent.TimeUnit

class PhoneLogService : SourceService<BaseSourceState>() {

    override val defaultState: BaseSourceState
        get() = BaseSourceState()

    override val isBluetoothConnectionRequired: Boolean = false

    override fun createSourceManager() = PhoneLogManager(this)

    override fun configureSourceManager(manager: SourceManager<BaseSourceState>, config: SingleRadarConfiguration) {
        manager as PhoneLogManager
        manager.setCallAndSmsLogUpdateRate(
                config.getLong(CALL_SMS_LOG_INTERVAL, CALL_SMS_LOG_INTERVAL_DEFAULT),
                TimeUnit.SECONDS)
    }

    companion object {
        private const val CALL_SMS_LOG_INTERVAL = "call_sms_log_interval_seconds"
        internal const val CALL_SMS_LOG_INTERVAL_DEFAULT = 3_600L // seconds
    }
}
