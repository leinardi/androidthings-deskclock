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

public class Avewind {

    @Json(name = "mph")
    private Float mph;
    @Json(name = "kph")
    private Float kph;
    @Json(name = "dir")
    private String dir;
    @Json(name = "degrees")
    private Float degrees;

    public Float getMph() {
        return mph;
    }

    public void setMph(Float mph) {
        this.mph = mph;
    }

    public Float getKph() {
        return kph;
    }

    public void setKph(Float kph) {
        this.kph = kph;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Float getDegrees() {
        return degrees;
    }

    public void setDegrees(Float degrees) {
        this.degrees = degrees;
    }

}
