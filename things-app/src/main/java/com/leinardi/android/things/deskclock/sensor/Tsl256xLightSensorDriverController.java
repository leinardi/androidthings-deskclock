/*
 * Copyright 2018 Roberto Leinardi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leinardi.android.things.deskclock.sensor;

import android.support.annotation.Nullable;

import com.leinardi.android.things.driver.tsl256x.Tsl256x;
import timber.log.Timber;

import java.io.IOException;

public class Tsl256xLightSensorDriverController extends LightSensorDriverController<Tsl256x> {
    @Nullable
    @Override
    public Float getLux() {
        Float lux = null;
        try {
            Tsl256x tsl256x = getDriver();
            tsl256x.setAutoGain(true);
            tsl256x.setIntegrationTime(Tsl256x.IntegrationTime.INTEGRATION_TIME_402MS);
            lux = tsl256x.readLux();
            Timber.d("L = %f", lux);
        } catch (IOException e) {
            Timber.e(e);
        }
        return lux;
    }
}
