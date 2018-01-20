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

package com.leinardi.android.things.deskclock.weather.model.wunderground.response;

import com.squareup.moshi.Json;

public class CurrentObservation {

    @Json(name = "image")
    private Image image;
    @Json(name = "display_location")
    private DisplayLocation displayLocation;
    @Json(name = "observation_location")
    private ObservationLocation observationLocation;
    @Json(name = "estimated")
    private Estimated estimated;
    @Json(name = "station_id")
    private String stationId;
    @Json(name = "observation_time")
    private String observationTime;
    @Json(name = "observation_time_rfc822")
    private String observationTimeRfc822;
    @Json(name = "observation_epoch")
    private String observationEpoch;
    @Json(name = "local_time_rfc822")
    private String localTimeRfc822;
    @Json(name = "local_epoch")
    private String localEpoch;
    @Json(name = "local_tz_short")
    private String localTzShort;
    @Json(name = "local_tz_long")
    private String localTzLong;
    @Json(name = "local_tz_offset")
    private String localTzOffset;
    @Json(name = "weather")
    private String weather;
    @Json(name = "temperature_string")
    private String temperatureString;
    @Json(name = "temp_f")
    private Float tempF;
    @Json(name = "temp_c")
    private Float tempC;
    @Json(name = "relative_humidity")
    private String relativeHumidity;
    @Json(name = "wind_string")
    private String windString;
    @Json(name = "wind_dir")
    private String windDir;
    @Json(name = "wind_degrees")
    private Float windDegrees;
    @Json(name = "wind_mph")
    private Float windMph;
    @Json(name = "wind_gust_mph")
    private String windGustMph;
    @Json(name = "wind_kph")
    private Float windKph;
    @Json(name = "wind_gust_kph")
    private String windGustKph;
    @Json(name = "pressure_mb")
    private String pressureMb;
    @Json(name = "pressure_in")
    private String pressureIn;
    @Json(name = "pressure_trend")
    private String pressureTrend;
    @Json(name = "dewpoint_string")
    private String dewpointString;
    @Json(name = "dewpoint_f")
    private Float dewpointF;
    @Json(name = "dewpoint_c")
    private Float dewpointC;
    @Json(name = "heat_index_string")
    private String heatIndexString;
    @Json(name = "heat_index_f")
    private String heatIndexF;
    @Json(name = "heat_index_c")
    private String heatIndexC;
    @Json(name = "windchill_string")
    private String windchillString;
    @Json(name = "windchill_f")
    private String windchillF;
    @Json(name = "windchill_c")
    private String windchillC;
    @Json(name = "feelslike_string")
    private String feelslikeString;
    @Json(name = "feelslike_f")
    private String feelslikeF;
    @Json(name = "feelslike_c")
    private String feelslikeC;
    @Json(name = "visibility_mi")
    private String visibilityMi;
    @Json(name = "visibility_km")
    private String visibilityKm;
    @Json(name = "solarradiation")
    private String solarradiation;
    @Json(name = "UV")
    private String uV;
    @Json(name = "precip_1hr_string")
    private String precip1hrString;
    @Json(name = "precip_1hr_in")
    private String precip1hrIn;
    @Json(name = "precip_1hr_metric")
    private String precip1hrMetric;
    @Json(name = "precip_today_string")
    private String precipTodayString;
    @Json(name = "precip_today_in")
    private String precipTodayIn;
    @Json(name = "precip_today_metric")
    private String precipTodayMetric;
    @Json(name = "icon")
    private String icon;
    @Json(name = "icon_url")
    private String iconUrl;
    @Json(name = "forecast_url")
    private String forecastUrl;
    @Json(name = "history_url")
    private String historyUrl;
    @Json(name = "ob_url")
    private String obUrl;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public DisplayLocation getDisplayLocation() {
        return displayLocation;
    }

    public void setDisplayLocation(DisplayLocation displayLocation) {
        this.displayLocation = displayLocation;
    }

    public ObservationLocation getObservationLocation() {
        return observationLocation;
    }

    public void setObservationLocation(ObservationLocation observationLocation) {
        this.observationLocation = observationLocation;
    }

    public Estimated getEstimated() {
        return estimated;
    }

    public void setEstimated(Estimated estimated) {
        this.estimated = estimated;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    public String getObservationTimeRfc822() {
        return observationTimeRfc822;
    }

    public void setObservationTimeRfc822(String observationTimeRfc822) {
        this.observationTimeRfc822 = observationTimeRfc822;
    }

    public String getObservationEpoch() {
        return observationEpoch;
    }

    public void setObservationEpoch(String observationEpoch) {
        this.observationEpoch = observationEpoch;
    }

    public String getLocalTimeRfc822() {
        return localTimeRfc822;
    }

    public void setLocalTimeRfc822(String localTimeRfc822) {
        this.localTimeRfc822 = localTimeRfc822;
    }

    public String getLocalEpoch() {
        return localEpoch;
    }

    public void setLocalEpoch(String localEpoch) {
        this.localEpoch = localEpoch;
    }

    public String getLocalTzShort() {
        return localTzShort;
    }

    public void setLocalTzShort(String localTzShort) {
        this.localTzShort = localTzShort;
    }

    public String getLocalTzLong() {
        return localTzLong;
    }

    public void setLocalTzLong(String localTzLong) {
        this.localTzLong = localTzLong;
    }

    public String getLocalTzOffset() {
        return localTzOffset;
    }

    public void setLocalTzOffset(String localTzOffset) {
        this.localTzOffset = localTzOffset;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperatureString() {
        return temperatureString;
    }

    public void setTemperatureString(String temperatureString) {
        this.temperatureString = temperatureString;
    }

    public Float getTempF() {
        return tempF;
    }

    public void setTempF(Float tempF) {
        this.tempF = tempF;
    }

    public Float getTempC() {
        return tempC;
    }

    public void setTempC(Float tempC) {
        this.tempC = tempC;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getWindString() {
        return windString;
    }

    public void setWindString(String windString) {
        this.windString = windString;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Float getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(Float windDegrees) {
        this.windDegrees = windDegrees;
    }

    public Float getWindMph() {
        return windMph;
    }

    public void setWindMph(Float windMph) {
        this.windMph = windMph;
    }

    public String getWindGustMph() {
        return windGustMph;
    }

    public void setWindGustMph(String windGustMph) {
        this.windGustMph = windGustMph;
    }

    public Float getWindKph() {
        return windKph;
    }

    public void setWindKph(Float windKph) {
        this.windKph = windKph;
    }

    public String getWindGustKph() {
        return windGustKph;
    }

    public void setWindGustKph(String windGustKph) {
        this.windGustKph = windGustKph;
    }

    public String getPressureMb() {
        return pressureMb;
    }

    public void setPressureMb(String pressureMb) {
        this.pressureMb = pressureMb;
    }

    public String getPressureIn() {
        return pressureIn;
    }

    public void setPressureIn(String pressureIn) {
        this.pressureIn = pressureIn;
    }

    public String getPressureTrend() {
        return pressureTrend;
    }

    public void setPressureTrend(String pressureTrend) {
        this.pressureTrend = pressureTrend;
    }

    public String getDewpointString() {
        return dewpointString;
    }

    public void setDewpointString(String dewpointString) {
        this.dewpointString = dewpointString;
    }

    public Float getDewpointF() {
        return dewpointF;
    }

    public void setDewpointF(Float dewpointF) {
        this.dewpointF = dewpointF;
    }

    public Float getDewpointC() {
        return dewpointC;
    }

    public void setDewpointC(Float dewpointC) {
        this.dewpointC = dewpointC;
    }

    public String getHeatIndexString() {
        return heatIndexString;
    }

    public void setHeatIndexString(String heatIndexString) {
        this.heatIndexString = heatIndexString;
    }

    public String getHeatIndexF() {
        return heatIndexF;
    }

    public void setHeatIndexF(String heatIndexF) {
        this.heatIndexF = heatIndexF;
    }

    public String getHeatIndexC() {
        return heatIndexC;
    }

    public void setHeatIndexC(String heatIndexC) {
        this.heatIndexC = heatIndexC;
    }

    public String getWindchillString() {
        return windchillString;
    }

    public void setWindchillString(String windchillString) {
        this.windchillString = windchillString;
    }

    public String getWindchillF() {
        return windchillF;
    }

    public void setWindchillF(String windchillF) {
        this.windchillF = windchillF;
    }

    public String getWindchillC() {
        return windchillC;
    }

    public void setWindchillC(String windchillC) {
        this.windchillC = windchillC;
    }

    public String getFeelslikeString() {
        return feelslikeString;
    }

    public void setFeelslikeString(String feelslikeString) {
        this.feelslikeString = feelslikeString;
    }

    public String getFeelslikeF() {
        return feelslikeF;
    }

    public void setFeelslikeF(String feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public String getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(String feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public String getVisibilityMi() {
        return visibilityMi;
    }

    public void setVisibilityMi(String visibilityMi) {
        this.visibilityMi = visibilityMi;
    }

    public String getVisibilityKm() {
        return visibilityKm;
    }

    public void setVisibilityKm(String visibilityKm) {
        this.visibilityKm = visibilityKm;
    }

    public String getSolarradiation() {
        return solarradiation;
    }

    public void setSolarradiation(String solarradiation) {
        this.solarradiation = solarradiation;
    }

    public String getUV() {
        return uV;
    }

    public void setUV(String uV) {
        this.uV = uV;
    }

    public String getPrecip1hrString() {
        return precip1hrString;
    }

    public void setPrecip1hrString(String precip1hrString) {
        this.precip1hrString = precip1hrString;
    }

    public String getPrecip1hrIn() {
        return precip1hrIn;
    }

    public void setPrecip1hrIn(String precip1hrIn) {
        this.precip1hrIn = precip1hrIn;
    }

    public String getPrecip1hrMetric() {
        return precip1hrMetric;
    }

    public void setPrecip1hrMetric(String precip1hrMetric) {
        this.precip1hrMetric = precip1hrMetric;
    }

    public String getPrecipTodayString() {
        return precipTodayString;
    }

    public void setPrecipTodayString(String precipTodayString) {
        this.precipTodayString = precipTodayString;
    }

    public String getPrecipTodayIn() {
        return precipTodayIn;
    }

    public void setPrecipTodayIn(String precipTodayIn) {
        this.precipTodayIn = precipTodayIn;
    }

    public String getPrecipTodayMetric() {
        return precipTodayMetric;
    }

    public void setPrecipTodayMetric(String precipTodayMetric) {
        this.precipTodayMetric = precipTodayMetric;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getForecastUrl() {
        return forecastUrl;
    }

    public void setForecastUrl(String forecastUrl) {
        this.forecastUrl = forecastUrl;
    }

    public String getHistoryUrl() {
        return historyUrl;
    }

    public void setHistoryUrl(String historyUrl) {
        this.historyUrl = historyUrl;
    }

    public String getObUrl() {
        return obUrl;
    }

    public void setObUrl(String obUrl) {
        this.obUrl = obUrl;
    }

}
