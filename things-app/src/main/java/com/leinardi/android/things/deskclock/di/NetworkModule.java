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

import com.leinardi.android.things.deskclock.BuildConfig;
import com.leinardi.android.things.deskclock.moshi.StringToFloatAdapter;
import com.leinardi.android.things.deskclock.moshi.StringToLongAdapter;
import com.leinardi.android.things.deskclock.weather.api.WundergroundApi;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

import static com.leinardi.android.things.deskclock.di.HostModule.NETWORK_TIMEOUT;
import static com.leinardi.android.things.deskclock.di.HostModule.WUNDERGROUND_URL;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(new StringToFloatAdapter())
                .add(new StringToLongAdapter())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(@Named(NETWORK_TIMEOUT) Long networkTimeout) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(logging);
        }

        return builder
                .connectTimeout(networkTimeout, TimeUnit.SECONDS)
                .readTimeout(networkTimeout, TimeUnit.SECONDS)
                .writeTimeout(networkTimeout, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public WundergroundApi provideWundergroundApi(@Named(WUNDERGROUND_URL) String baseUrl, OkHttpClient client,
                                                  Moshi moshi) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build().create(WundergroundApi.class);
    }

}
