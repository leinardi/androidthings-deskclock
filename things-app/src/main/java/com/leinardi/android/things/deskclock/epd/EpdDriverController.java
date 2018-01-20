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

package com.leinardi.android.things.deskclock.epd;

import android.graphics.Bitmap;

import com.leinardi.android.things.deskclock.controller.BaseDriverController;

public abstract class EpdDriverController<D extends AutoCloseable> extends BaseDriverController<D> {

    public abstract void show(Bitmap bitmap);

    /**
     * Invert the display color without rewriting the contents of the display data RAM..
     *
     * @param invert Set to true to invert the display color; set to false to set the display back to normal.
     * @throws IllegalStateException
     */
    public abstract void invertDisplay(boolean invert);
}
