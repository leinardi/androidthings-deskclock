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

package com.leinardi.android.things.deskclock.controller;

import android.os.Handler;
import android.os.LocaleList;
import android.os.PowerManager;

import com.google.android.things.device.DeviceManager;
import com.google.android.things.device.TimeManager;
import com.leinardi.android.things.deskclock.epd.EpdDriverController;
import com.leinardi.android.things.deskclock.epd.EpdHelper;
import com.leinardi.android.things.deskclock.sensor.SensorData;
import com.leinardi.android.things.deskclock.sensor.SensorRepository;
import com.leinardi.android.things.deskclock.weather.WeatherRepository;
import com.leinardi.android.things.deskclock.weather.model.Weather;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DeskClockController implements BaseController {
    private static final int SCREEN_REFRESH_INTERVAL_IN_MINUTES = 5;
    private static final int WEATHER_REFRESH_INTERVAL_IN_MINUTES = 20;
    private static final int WEATHER_ADVANCE_TIME_IN_SECONDS = 5;
    private static final long WAKE_LOCK_TIMEOUT = TimeUnit.SECONDS.toMillis(10);
    private static final float INVERT_DISPLAY_THRESHOLD = 1.5f;
    private final EpdDriverController mEpdDriverController;
    private final EpdHelper mEpdHelper;
    private final SensorRepository mSensorRepository;
    private final WeatherRepository mWeatherRepository;
    private final PowerManager mPowerManager;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private final Handler mHandler = new Handler();

    private final Runnable mHandlerClockRefreshTask = () -> {
        scheduleNextClockRefresh();
        refreshDisplay();
    };

    private final Runnable mHandlerWeatherRefreshTask = () -> {
        scheduleNextWeatherRefresh();
        fetchWeather();
    };

    @Inject
    public DeskClockController(EpdDriverController epdDriverController,
                               EpdHelper epdHelper,
                               SensorRepository sensorRepository,
                               WeatherRepository weatherRepository,
                               PowerManager powerManager) {
        mEpdDriverController = epdDriverController;
        mEpdHelper = epdHelper;
        mSensorRepository = sensorRepository;
        mWeatherRepository = weatherRepository;
        mPowerManager = powerManager;
    }

    private void scheduleNextClockRefresh() {
        acquireTemporaryWakeLock();
        long currentTimeMillis = System.currentTimeMillis();
        Calendar nextTick = Calendar.getInstance();
        nextTick.setTimeInMillis(currentTimeMillis);
        int min = nextTick.get(Calendar.MINUTE);
        min -= min % SCREEN_REFRESH_INTERVAL_IN_MINUTES;
        min += SCREEN_REFRESH_INTERVAL_IN_MINUTES;
        nextTick.set(Calendar.MINUTE, min);
        nextTick.set(Calendar.SECOND, 0);
        Timber.d("Next tick scheduled for %s", nextTick.getTime());
        mHandler.postDelayed(mHandlerClockRefreshTask, nextTick.getTimeInMillis() - currentTimeMillis);
    }

    private void acquireTemporaryWakeLock() {
        Timber.d("acquireTemporaryWakeLock()");
        mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                DeskClockController.class.getName()).acquire(WAKE_LOCK_TIMEOUT);
    }

    private void scheduleNextWeatherRefresh() {
        acquireTemporaryWakeLock();
        long currentTimeMillis = System.currentTimeMillis();
        Calendar nextTick = Calendar.getInstance();
        nextTick.setTimeInMillis(System.currentTimeMillis());
        nextTick.add(Calendar.SECOND, WEATHER_ADVANCE_TIME_IN_SECONDS);
        int min = nextTick.get(Calendar.MINUTE);
        min -= min % WEATHER_REFRESH_INTERVAL_IN_MINUTES;
        min += WEATHER_REFRESH_INTERVAL_IN_MINUTES;
        nextTick.set(Calendar.MINUTE, min);
        nextTick.set(Calendar.SECOND, 0);
        nextTick.add(Calendar.SECOND, -WEATHER_ADVANCE_TIME_IN_SECONDS); // To be executed before the screen refresh
        Timber.d("Next weather scheduled for %s", nextTick.getTime());
        mHandler.postDelayed(mHandlerWeatherRefreshTask, nextTick.getTimeInMillis() - currentTimeMillis);
    }

    public void start() {
        Timber.d("start");
        mCompositeDisposable.add(mWeatherRepository.refreshWeathers()
                .subscribeWith(new DisposableSingleObserver<Weather[]>() {
                    @Override
                    public void onSuccess(Weather[] weathers) {
                        mHandlerClockRefreshTask.run();
                        scheduleNextWeatherRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);
                        mHandlerClockRefreshTask.run();
                        scheduleNextWeatherRefresh();
                    }
                }));
    }

    public synchronized void refreshDisplay() {
        Timber.d("refreshDisplay");
        SensorData sensorData = mSensorRepository.getSensorData();
        mEpdDriverController.invertDisplay(sensorData.getLux() < INVERT_DISPLAY_THRESHOLD);
        mEpdDriverController.show(mEpdHelper.getBitmap(sensorData, mWeatherRepository.getWeathers()));
    }

    public void setLocale(Locale... locales) {
        new DeviceManager().setSystemLocales(new LocaleList(locales));
    }

    public void setTimeZone(String timeZone) {
        TimeManager timeManager = new TimeManager();
        timeManager.setTimeZone(timeZone);
        timeManager.setAutoTimeEnabled(true);
    }

    public synchronized void fetchWeather() {
        Timber.d("fetchWeather");
        mCompositeDisposable.add(mWeatherRepository.refreshWeathers().subscribe());
    }

    @Override
    public void close() {
        Timber.d("close");
        mHandler.removeCallbacks(mHandlerClockRefreshTask);
        mHandler.removeCallbacks(mHandlerWeatherRefreshTask);
        mCompositeDisposable.clear();
    }
}
