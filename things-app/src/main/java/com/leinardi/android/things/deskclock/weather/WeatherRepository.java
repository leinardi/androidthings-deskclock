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

package com.leinardi.android.things.deskclock.weather;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.leinardi.android.things.deskclock.R;
import com.leinardi.android.things.deskclock.config.ConfigRepository;
import com.leinardi.android.things.deskclock.util.WebApplicationException;
import com.leinardi.android.things.deskclock.weather.api.WundergroundApi;
import com.leinardi.android.things.deskclock.weather.model.Weather;
import com.leinardi.android.things.deskclock.weather.model.wunderground.response.ConditionsResponse;
import com.leinardi.android.things.deskclock.weather.model.wunderground.response.Error;
import com.leinardi.android.things.deskclock.weather.model.wunderground.response.ForecastResponse;
import com.leinardi.android.things.deskclock.weather.model.wunderground.response.Simpleforecastday;
import com.leinardi.android.things.deskclock.weather.model.wunderground.response.WundergroundResponse;
import io.reactivex.Single;
import timber.log.Timber;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class WeatherRepository {
    private WundergroundApi mWundergroundApi;
    private ConfigRepository mConfigRepository;
    private Weather[] mWeathers;

    @Inject
    public WeatherRepository(WundergroundApi wundergroundApi, ConfigRepository configRepository) {
        mWundergroundApi = wundergroundApi;
        mConfigRepository = configRepository;
    }

    public Single<Weather[]> refreshWeathers() {
        return Single.zip(
                mWundergroundApi.getForecastResponseSingle(mConfigRepository.getLanguage(), mConfigRepository
                        .getLocation()),
                mWundergroundApi.getConditionsResponseSingle(mConfigRepository.getLanguage(), mConfigRepository
                        .getLocation()),
                (forecastResponse, conditionsResponse) -> {
                    checkForErrors(forecastResponse);
                    checkForErrors(conditionsResponse);
                    return updateWeathers(forecastResponse, conditionsResponse);
                });
    }

    @NonNull
    private void checkForErrors(WundergroundResponse response) {
        Error error = response.getResponse().getError();
        if (error != null) {
            throw new WebApplicationException(error.getDescription(), response);
        }
    }

    private Weather[] updateWeathers(ForecastResponse forecastResponse, ConditionsResponse conditionsResponse) {
        Weather[] weathers = null;
        if (forecastResponse.getForecast() != null
                && forecastResponse.getForecast().getSimpleforecast() != null
                && forecastResponse.getForecast().getSimpleforecast().getForecastday() != null) {
            List<Simpleforecastday> forecastdayList = forecastResponse.getForecast().getSimpleforecast()
                    .getForecastday();
            weathers = new Weather[forecastdayList.size()];
            for (int i = 0; i < forecastdayList.size(); i++) {
                Simpleforecastday forecastday = forecastdayList.get(i);
                Date date = new Date(TimeUnit.SECONDS.toMillis(forecastday.getDate().getEpoch()));
                int wundergroundIcon = getWundergroundIcon(forecastday.getIcon());
                int high = Math.round(forecastday.getHigh().getCelsius());
                int tempLow = Math.round(forecastday.getLow().getCelsius());
                int temp;
                if (i == 0 && conditionsResponse.getCurrentObservation() != null
                        && conditionsResponse.getCurrentObservation().getTempC() != null) {
                    temp = Math.round(conditionsResponse.getCurrentObservation().getTempC());
                } else {
                    temp = Math.round(forecastday.getHigh().getCelsius());
                }
                String conditions = forecastday.getConditions();
                weathers[i] = new Weather(date, wundergroundIcon, high, tempLow, temp, conditions);

            }
        }
        mWeathers = weathers;
        return weathers;
    }

    @DrawableRes
    private int getWundergroundIcon(String iconName) {
        switch (iconName) {
            case "chanceflurries":
                return R.drawable.weather_chance_flurries;
            case "chancerain":
                return R.drawable.weather_chance_rain;
            case "chancesleet":
                return R.drawable.weather_chance_sleet;
            case "chancesnow":
                return R.drawable.weather_chance_snow;
            case "chancetstorms":
                return R.drawable.weather_chance_storm;
            case "clear":
                return R.drawable.weather_sunny;
            case "cloudy":
                return R.drawable.weather_cloudy;
            case "flurries":
                return R.drawable.weather_flurries;
            case "fog":
                return R.drawable.weather_fog;
            case "hazy":
                return R.drawable.weather_hazy;
            case "mostlycloudy":
                return R.drawable.weather_partly_cloudy;
            case "mostlysunny":
                return R.drawable.weather_partly_cloudy;
            case "partlycloudy":
                return R.drawable.weather_partly_cloudy;
            case "partlysunny":
                return R.drawable.weather_partly_cloudy;
            case "rain":
                return R.drawable.weather_rain;
            case "sleet":
                return R.drawable.weather_sleet;
            case "snow":
                return R.drawable.weather_snow;
            case "sunny":
                return R.drawable.weather_sunny;
            case "tstorms":
                return R.drawable.weather_storm;
            default:
                Timber.e("Unknown iconName %s", iconName);
                return R.drawable.weather_unknown;
        }
    }

    public Weather[] getWeathers() {
        return mWeathers;
    }
}
