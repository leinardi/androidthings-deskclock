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

public class SensorData {
    private final int mTemperature;
    private final int mPressure;
    private final int mHumidity;
    private final float mLux;

    public SensorData(int temperature, int pressure, int humidity, float lux) {
        this.mTemperature = temperature;
        this.mPressure = pressure;
        this.mHumidity = humidity;
        this.mLux = lux;
    }

    public int getTemperature() {
        return mTemperature;
    }

    public int getPressure() {
        return mPressure;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public float getLux() {
        return mLux;
    }
}
