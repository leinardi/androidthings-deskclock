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

package com.leinardi.android.things.deskclock.ui;

import android.app.Application;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.leinardi.android.things.deskclock.R;
import com.leinardi.android.things.deskclock.databinding.MainActivityBinding;
import com.leinardi.android.things.deskclock.di.Injectable;
import com.leinardi.android.things.deskclock.service.DeskClockService;
import timber.log.Timber;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel> implements Injectable {

    @Inject
    Application mApplication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setViewModel(getViewModel());

        startCarService();
    }

    private void startCarService() {
        Intent startIntent = new Intent(mApplication, DeskClockService.class);
        startIntent.setAction(DeskClockService.ACTION_START_DESK_CLOCK_SERVICE);
        ContextCompat.startForegroundService(mApplication, startIntent);
    }

    private void stopCarService() {
        Intent stopIntent = new Intent(mApplication, DeskClockService.class);
        stopIntent.setAction(DeskClockService.ACTION_STOP_DESK_CLOCK_SERVICE);
        ContextCompat.startForegroundService(mApplication, stopIntent);
    }

    @Override
    protected void onViewModelPropertyChanged(Observable observable, int propertyId) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            stopCarService();
        }
    }
}
