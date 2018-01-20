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

package com.leinardi.android.things.deskclock.moshi;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class StringToLongAdapter {
    @FromJson
    @StringToLong
    long fromJson(String value) {
        return Long.parseLong(value);
    }

    @ToJson
    String toJson(@StringToLong long value) {
        return Long.toString(value);
    }
}
