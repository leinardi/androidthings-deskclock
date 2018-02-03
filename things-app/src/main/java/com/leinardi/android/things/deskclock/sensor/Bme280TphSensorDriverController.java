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

import android.os.SystemClock;

import com.google.android.things.contrib.driver.bmx280.Bmx280;
import timber.log.Timber;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Bme280TphSensorDriverController extends TphSensorDriverController<Bmx280> {

    private static final long DATA_VALIDITY_TIME = TimeUnit.SECONDS.toMillis(1);
    private float mTemperature;
    private float mPressure;
    private float mHumidity;
    private long mLastRefreshTime;

    @Override

    public Float getTemperature() {
        refreshIfOutdated();
        return mTemperature;
    }

    @Override
    public Float getPressure() {
        refreshIfOutdated();
        return mPressure;
    }

    @Override
    public Float getHumidity() {
        refreshIfOutdated();
        return mHumidity;
    }

    private void refreshIfOutdated() {
        if (System.currentTimeMillis() - mLastRefreshTime > DATA_VALIDITY_TIME) {
            Timber.d("Data obsolete");
            refreshData();
        }
    }

    private void refreshData() {
        Timber.d("Refreshing data");
        if (isHardwareAvailable()) {
            Bmx280 bmx280 = getDriver();
            try {
                bmx280.setTemperatureOversampling(Bmx280.OVERSAMPLING_1X);
                bmx280.setPressureOversampling(Bmx280.OVERSAMPLING_1X);
                bmx280.setHumidityOversampling(Bmx280.OVERSAMPLING_1X);

                for (int i = 0; i < 2; i++) {
                    // The first read is inaccurate
                    mTemperature = bmx280.readTemperature();
                    mPressure = bmx280.readPressure();
                    mHumidity = bmx280.readHumidity();
                    Timber.d("T = %f, P = %f, U = %f", mTemperature, mPressure, mHumidity);
                    mLastRefreshTime = System.currentTimeMillis();
                    SystemClock.sleep(100);
                }

                bmx280.setMode(Bmx280.MODE_SLEEP);
            } catch (IOException e) {
                Timber.e(e);
            }

        }
    }
}
