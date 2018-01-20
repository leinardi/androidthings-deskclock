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

import android.text.format.DateUtils;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class HostModule {
    private static final int NETWORK_TIMEOUT_SECONDS = 10;
    public static final String WUNDERGROUND_URL = "pixabayUrl";
    public static final String NETWORK_TIMEOUT = "networkTimeout";

    @Provides
    @Singleton
    @Named(WUNDERGROUND_URL)
    public String provideBaseUrl() {
        return "http://api.wunderground.com/";
    }

    @Provides
    @Singleton
    @Named(NETWORK_TIMEOUT)
    public Long provideNetworkTimeout() {
        return NETWORK_TIMEOUT_SECONDS * DateUtils.SECOND_IN_MILLIS;
    }
}
