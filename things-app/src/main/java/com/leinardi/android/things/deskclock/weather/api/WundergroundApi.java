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

package com.leinardi.android.things.deskclock.weather.api;

import com.leinardi.android.things.deskclock.BuildConfig;
import com.leinardi.android.things.deskclock.weather.model.wunderground.response.ConditionsResponse;
import com.leinardi.android.things.deskclock.weather.model.wunderground.response.ForecastResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WundergroundApi {
    @GET("api/" + BuildConfig.WUNDERGROUND_API_KEY + "/conditions/lang:{lang}/q/{query}.json")
    Single<ConditionsResponse> getConditionsResponseSingle(@Path("lang") String lang, @Path("query") String query);

    @GET("api/" + BuildConfig.WUNDERGROUND_API_KEY + "/forecast/lang:{lang}/q/{query}.json")
    Single<ForecastResponse> getForecastResponseSingle(@Path("lang") String lang, @Path("query") String query);
}
