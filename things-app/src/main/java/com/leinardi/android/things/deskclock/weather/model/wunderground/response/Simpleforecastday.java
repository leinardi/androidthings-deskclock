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

public class Simpleforecastday {

    @Json(name = "date")
    private Date date;
    @Json(name = "period")
    private Float period;
    @Json(name = "high")
    private High high;
    @Json(name = "low")
    private Low low;
    @Json(name = "conditions")
    private String conditions;
    @Json(name = "icon")
    private String icon;
    @Json(name = "icon_url")
    private String iconUrl;
    @Json(name = "skyicon")
    private String skyicon;
    @Json(name = "pop")
    private Float pop;
    @Json(name = "qpf_allday")
    private QpfAllday qpfAllday;
    @Json(name = "qpf_day")
    private QpfDay qpfDay;
    @Json(name = "qpf_night")
    private QpfNight qpfNight;
    @Json(name = "snow_allday")
    private SnowAllday snowAllday;
    @Json(name = "snow_day")
    private SnowDay snowDay;
    @Json(name = "snow_night")
    private SnowNight snowNight;
    @Json(name = "maxwind")
    private Maxwind maxwind;
    @Json(name = "avewind")
    private Avewind avewind;
    @Json(name = "avehumidity")
    private Float avehumidity;
    @Json(name = "maxhumidity")
    private Float maxhumidity;
    @Json(name = "minhumidity")
    private Float minhumidity;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPeriod() {
        return period;
    }

    public void setPeriod(Float period) {
        this.period = period;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }

    public Low getLow() {
        return low;
    }

    public void setLow(Low low) {
        this.low = low;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
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

    public String getSkyicon() {
        return skyicon;
    }

    public void setSkyicon(String skyicon) {
        this.skyicon = skyicon;
    }

    public Float getPop() {
        return pop;
    }

    public void setPop(Float pop) {
        this.pop = pop;
    }

    public QpfAllday getQpfAllday() {
        return qpfAllday;
    }

    public void setQpfAllday(QpfAllday qpfAllday) {
        this.qpfAllday = qpfAllday;
    }

    public QpfDay getQpfDay() {
        return qpfDay;
    }

    public void setQpfDay(QpfDay qpfDay) {
        this.qpfDay = qpfDay;
    }

    public QpfNight getQpfNight() {
        return qpfNight;
    }

    public void setQpfNight(QpfNight qpfNight) {
        this.qpfNight = qpfNight;
    }

    public SnowAllday getSnowAllday() {
        return snowAllday;
    }

    public void setSnowAllday(SnowAllday snowAllday) {
        this.snowAllday = snowAllday;
    }

    public SnowDay getSnowDay() {
        return snowDay;
    }

    public void setSnowDay(SnowDay snowDay) {
        this.snowDay = snowDay;
    }

    public SnowNight getSnowNight() {
        return snowNight;
    }

    public void setSnowNight(SnowNight snowNight) {
        this.snowNight = snowNight;
    }

    public Maxwind getMaxwind() {
        return maxwind;
    }

    public void setMaxwind(Maxwind maxwind) {
        this.maxwind = maxwind;
    }

    public Avewind getAvewind() {
        return avewind;
    }

    public void setAvewind(Avewind avewind) {
        this.avewind = avewind;
    }

    public Float getAvehumidity() {
        return avehumidity;
    }

    public void setAvehumidity(Float avehumidity) {
        this.avehumidity = avehumidity;
    }

    public Float getMaxhumidity() {
        return maxhumidity;
    }

    public void setMaxhumidity(Float maxhumidity) {
        this.maxhumidity = maxhumidity;
    }

    public Float getMinhumidity() {
        return minhumidity;
    }

    public void setMinhumidity(Float minhumidity) {
        this.minhumidity = minhumidity;
    }

}
