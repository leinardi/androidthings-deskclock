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

package com.leinardi.android.things.deskclock.weather.model;

import android.support.annotation.DrawableRes;

import java.util.Date;

public class Weather {
    private final Date mDate;
    @DrawableRes
    private final int mIcon;
    private final int mTempHigh;
    private final int mTempLow;
    private final int mTemp;
    private final String mConditions;

    public Weather(Date date, int icon, int tempHigh, int tempLow, int temp, String conditions) {
        this.mDate = date;
        this.mIcon = icon;
        this.mTempHigh = tempHigh;
        this.mTempLow = tempLow;
        this.mTemp = temp;
        this.mConditions = conditions;
    }

    public Date getDate() {
        return mDate;
    }

    public int getIcon() {
        return mIcon;
    }

    public int getTempHigh() {
        return mTempHigh;
    }

    public int getTempLow() {
        return mTempLow;
    }

    public int getTemp() {
        return mTemp;
    }

    public String getConditions() {
        return mConditions;
    }
}
