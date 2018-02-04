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

package com.leinardi.android.things.deskclock.di;

import android.support.annotation.Nullable;

import com.google.android.things.contrib.driver.bmx280.Bmx280;
import com.leinardi.android.things.deskclock.epd.EpdDriverController;
import com.leinardi.android.things.deskclock.epd.Gdew075t8EpdDriverController;
import com.leinardi.android.things.deskclock.sensor.Bme280TphSensorDriverController;
import com.leinardi.android.things.deskclock.sensor.LightSensorDriverController;
import com.leinardi.android.things.deskclock.sensor.TphSensorDriverController;
import com.leinardi.android.things.deskclock.sensor.Tsl256xLightSensorDriverController;
import com.leinardi.android.things.deskclock.util.BoardDefaults;
import com.leinardi.android.things.driver.epaperdriverhat.Gdew075t8Epd;
import com.leinardi.android.things.driver.tsl256x.Tsl256x;
import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

import javax.inject.Singleton;
import java.io.IOException;

@Module
public class AndroidThingsModule {
    private static final int BME280_ADDRESS = 0x76;
    private static final int TSL2561_ADDRESS = 0x39;

    @Singleton
    @Provides
    @Nullable
    Gdew075t8Epd provideGdew075t8Epd() {
        try {
            return new Gdew075t8Epd();
        } catch (IOException e) {
            Timber.e(e);
            return null;
        }
    }

    @Singleton
    @Provides
    EpdDriverController provideEpdDriverController(@Nullable Gdew075t8Epd epd) {
        Gdew075t8EpdDriverController controller = new Gdew075t8EpdDriverController();
        controller.setDriver(epd);
        return controller;
    }

    @Singleton
    @Provides
    @Nullable
    Bmx280 provideBmx280() {
        try {
            return new Bmx280(BoardDefaults.getI2CPort(), BME280_ADDRESS);
        } catch (IOException e) {
            Timber.e(e);
            return null;
        }
    }

    @Singleton
    @Provides
    TphSensorDriverController provideTphSensorDriverController(@Nullable Bmx280 bmx280) {
        Bme280TphSensorDriverController controller = new Bme280TphSensorDriverController();
        controller.setDriver(bmx280);
        return controller;
    }

    @Singleton
    @Provides
    @Nullable
    Tsl256x provideTsl256x() {
        try {
            return new Tsl256x(BoardDefaults.getI2CPort(), TSL2561_ADDRESS);
        } catch (IOException e) {
            Timber.e(e);
            return null;
        }
    }

    @Singleton
    @Provides
    LightSensorDriverController provideLightSensorDriverController(@Nullable Tsl256x tsl256x) {
        Tsl256xLightSensorDriverController controller = new Tsl256xLightSensorDriverController();
        controller.setDriver(tsl256x);
        return controller;
    }
}
