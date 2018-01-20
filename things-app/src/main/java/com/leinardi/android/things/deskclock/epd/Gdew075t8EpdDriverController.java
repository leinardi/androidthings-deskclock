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
import android.os.Environment;

import com.leinardi.android.things.driver.epaperdriverhat.BitmapHelper;
import com.leinardi.android.things.driver.epaperdriverhat.Gdew075t8Epd;
import timber.log.Timber;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Gdew075t8EpdDriverController extends EpdDriverController<Gdew075t8Epd> {

    public Gdew075t8EpdDriverController() {
    }

    @Override
    public void show(Bitmap bitmap) {
        if (isHardwareAvailable()) {
            Gdew075t8Epd epd = getDriver();
            BitmapHelper.setBmpData(epd, 0, 0, bitmap, false);
            saveBitmapToSdcard(bitmap);
            try {
                Timber.d("Updating edp");
                epd.show();
            } catch (IOException e) {
                Timber.e(e);
            }
        } else {
            Timber.w("Hardware not available!");
        }
    }

    private void saveBitmapToSdcard(Bitmap bitmap) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/DeskClock");
        myDir.mkdirs();
        String fileName = "DeskClock.jpg";
        File file = new File(myDir, fileName);
        Timber.d("Saving %s", file);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    @Override
    public void invertDisplay(boolean invert) {
        if (isHardwareAvailable()) {
            try {
                getDriver().setInvertDisplay(invert);
            } catch (IOException e) {
                Timber.e(e);
            }
        } else {
            Timber.w("Hardware not available!");
        }
    }
}
