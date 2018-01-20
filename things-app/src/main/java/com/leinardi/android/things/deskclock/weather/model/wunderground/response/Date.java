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

import com.leinardi.android.things.deskclock.moshi.StringToLong;
import com.squareup.moshi.Json;

public class Date {

    @Json(name = "epoch")
    @StringToLong
    private long epoch;
    @Json(name = "pretty")
    private String pretty;
    @Json(name = "day")
    private Float day;
    @Json(name = "month")
    private Float month;
    @Json(name = "year")
    private Float year;
    @Json(name = "yday")
    private Float yday;
    @Json(name = "hour")
    private Float hour;
    @Json(name = "min")
    private String min;
    @Json(name = "sec")
    private Float sec;
    @Json(name = "isdst")
    private String isdst;
    @Json(name = "monthname")
    private String monthname;
    @Json(name = "weekday_short")
    private String weekdayShort;
    @Json(name = "weekday")
    private String weekday;
    @Json(name = "ampm")
    private String ampm;
    @Json(name = "tz_short")
    private String tzShort;
    @Json(name = "tz_long")
    private String tzLong;

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public String getPretty() {
        return pretty;
    }

    public void setPretty(String pretty) {
        this.pretty = pretty;
    }

    public Float getDay() {
        return day;
    }

    public void setDay(Float day) {
        this.day = day;
    }

    public Float getMonth() {
        return month;
    }

    public void setMonth(Float month) {
        this.month = month;
    }

    public Float getYear() {
        return year;
    }

    public void setYear(Float year) {
        this.year = year;
    }

    public Float getYday() {
        return yday;
    }

    public void setYday(Float yday) {
        this.yday = yday;
    }

    public Float getHour() {
        return hour;
    }

    public void setHour(Float hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public Float getSec() {
        return sec;
    }

    public void setSec(Float sec) {
        this.sec = sec;
    }

    public String getIsdst() {
        return isdst;
    }

    public void setIsdst(String isdst) {
        this.isdst = isdst;
    }

    public String getMonthname() {
        return monthname;
    }

    public void setMonthname(String monthname) {
        this.monthname = monthname;
    }

    public String getWeekdayShort() {
        return weekdayShort;
    }

    public void setWeekdayShort(String weekdayShort) {
        this.weekdayShort = weekdayShort;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getTzShort() {
        return tzShort;
    }

    public void setTzShort(String tzShort) {
        this.tzShort = tzShort;
    }

    public String getTzLong() {
        return tzLong;
    }

    public void setTzLong(String tzLong) {
        this.tzLong = tzLong;
    }

}
