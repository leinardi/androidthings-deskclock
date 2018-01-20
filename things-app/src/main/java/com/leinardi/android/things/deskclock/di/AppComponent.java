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

import android.app.Application;
import android.support.annotation.Nullable;

import com.leinardi.android.things.deskclock.ThingsApp;
import com.leinardi.android.things.driver.epaperdriverhat.Gdew075t8Epd;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidModule.class,
        AndroidThingsModule.class,
        ApiModule.class,
        AppModule.class,
        DeskClockServiceModule.class,
        HostModule.class,
        MainActivityModule.class,
        NetworkModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder epd(@Nullable Gdew075t8Epd gdew075t8Epd);

        AppComponent build();
    }

    void inject(ThingsApp target);
}
