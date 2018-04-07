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

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.MonthDisplayHelper;

import com.leinardi.android.things.deskclock.R;
import com.leinardi.android.things.deskclock.sensor.SensorData;
import com.leinardi.android.things.deskclock.util.SystemInfoHelper;
import com.leinardi.android.things.deskclock.weather.model.Weather;

import javax.inject.Inject;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EpdHelper {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 384;
    private static final int TEXT_SIZE_SMALLEST = 8;
    private static final int TEXT_SIZE_SMALL = TEXT_SIZE_SMALLEST * 2;
    private static final int TEXT_SIZE_MEDIUM = TEXT_SIZE_SMALLEST * 3;
    private static final int TEXT_SIZE_MEDIUM_LARGE = TEXT_SIZE_SMALLEST * 4;
    private static final int TEXT_SIZE_LARGE = TEXT_SIZE_SMALLEST * 7;
    private static final int TEXT_SIZE_EXTRA_LARGE = TEXT_SIZE_SMALLEST * 10;
    private static final int TEXT_SIZE_HUGE = TEXT_SIZE_SMALLEST * 16;
    private static final int TEXT_PADDING = 4;
    private static final int TIME_CENTER_X = 220;
    private static final int TIME_CENTER_Y = 4;

    private static final int SENSORS_LEFT_X = 16;
    private static final int SENSORS_LEFT_Y = TIME_CENTER_Y + TEXT_SIZE_HUGE + 20;

    private static final int WEATHER_LEFT_X = SENSORS_LEFT_X;
    private static final int WEATHER_LEFT_Y = TIME_CENTER_Y + TEXT_SIZE_HUGE + 132;
    private static final int WEATHER_CONDITIONS_MAX_LENGTH = 410;
    private static final int FORECAST_CELL_WIDTH = 92;
    private static final int FORECAST_DAYS = 3;

    private static final int CALENDAR_LEFT_X = WIDTH - 190;
    private static final int CALENDAR_LEFT_Y = 22;
    private static final int CALENDAR_CELL_WIDTH = 26;
    private static final int CALENDAR_CELL_HEIGHT = 20;

    private final SimpleDateFormat mTime24HDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private final SimpleDateFormat mTime12HDateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
    private final SimpleDateFormat mAmPmDateFormat = new SimpleDateFormat("a", Locale.getDefault());
    private final SimpleDateFormat mDayOfMonthDateFormat = new SimpleDateFormat("dd", Locale.getDefault());
    private final SimpleDateFormat mDayOfWeekShortDateFormat = new SimpleDateFormat("E", Locale.getDefault());
    private final SimpleDateFormat mDayOfWeekLongDateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
    private final SimpleDateFormat mMonthDateFormat = new SimpleDateFormat("MMMM", Locale.getDefault());

    private static final int DAYS_IN_A_WEEK = 7;
    private final SystemInfoHelper mSystemInfoHelper;
    private TextPaint mTextPaint;
    private Bitmap mTextAsBitmap;
    private Canvas mCanvas;
    private Calendar mCalendar;
    private Context mContext;

    @Inject
    public EpdHelper(Application application,
                     SystemInfoHelper systemInfoHelper) {
        mContext = application.getApplicationContext();
        mSystemInfoHelper = systemInfoHelper;
        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(TEXT_SIZE_SMALLEST);
        mTextPaint.setTypeface(ResourcesCompat.getFont(mContext, R.font.epd_font));
        mTextPaint.setColor(Color.WHITE);
        clearCanvas();
    }

    private void clearCanvas() {
        mCalendar = Calendar.getInstance();

//        mCalendar.set(2018, Calendar.SEPTEMBER, 12);

        mTextAsBitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mTextAsBitmap);
        mCanvas.setDrawFilter(new PaintFlagsDrawFilter(Paint.FILTER_BITMAP_FLAG, 0));
    }

    public Bitmap getBitmap(SensorData sensorData, Weather[] weathers) {
        clearCanvas();
        drawScreen(sensorData, weathers);
        return mTextAsBitmap;
    }

    private void drawScreen(SensorData sensorData, Weather[] weathers) {
        drawTime();
        drawCalendar();
        drawSensorData(sensorData);
        drawWeather(weathers);
        drawFooter();
    }

    private void drawTime() {
        if (!DateFormat.is24HourFormat(mContext)) {
            String amPmText = mAmPmDateFormat.format(mCalendar.getTime());
            int amPmWidth = measureText(TEXT_SIZE_MEDIUM, amPmText);
            int timeCenterX = TIME_CENTER_X + amPmWidth / 2;
            int timeWidth = drawText(Paint.Align.CENTER, TEXT_SIZE_HUGE, mTime12HDateFormat.format(
                    mCalendar.getTime()), timeCenterX, TIME_CENTER_Y);
            int amPmX = TIME_CENTER_X - (timeWidth / 2) + (TEXT_SIZE_MEDIUM / 7);
            int amPmY = TIME_CENTER_Y + getTextSizeBaseline(TEXT_SIZE_HUGE);
            drawText(Paint.Align.RIGHT, TEXT_SIZE_MEDIUM, amPmText, amPmX, amPmY, false);
        } else {
            drawText(Paint.Align.CENTER, TEXT_SIZE_HUGE, mTime24HDateFormat.format(mCalendar.getTime()),
                    TIME_CENTER_X, TIME_CENTER_Y);
        }
    }

    private void drawCalendar() {
        MonthDisplayHelper monthDisplayHelper = new MonthDisplayHelper(mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.getFirstDayOfWeek());

        String dayOfMonth = mDayOfMonthDateFormat.format(mCalendar.getTime());
        String dayOfWeekLong = mDayOfWeekLongDateFormat.format(mCalendar.getTime()).toUpperCase();
        String month = mMonthDateFormat.format(mCalendar.getTime());
        month = month.substring(0, 1).toUpperCase() + month.substring(1);

        int dayWidth = drawText(Paint.Align.LEFT, TEXT_SIZE_LARGE, dayOfMonth, CALENDAR_LEFT_X, CALENDAR_LEFT_Y);
        drawText(Paint.Align.LEFT, TEXT_SIZE_MEDIUM, month, CALENDAR_LEFT_X + dayWidth + 2, CALENDAR_LEFT_Y +
                getTextCapsHeightFromTop(TEXT_SIZE_MEDIUM) + 2);
        drawText(Paint.Align.LEFT, TEXT_SIZE_SMALL, dayOfWeekLong, CALENDAR_LEFT_X + dayWidth + 2,
                CALENDAR_LEFT_Y +
                        getTextSizeBaseline(TEXT_SIZE_LARGE) - getTextSizeBaseline(TEXT_SIZE_SMALL));

        DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance();
        String[] dfsWeekdays = dateFormatSymbols.getWeekdays();

        String[] weekdays = new String[DAYS_IN_A_WEEK];
        for (int i = 0; i < weekdays.length; i++) {
            int dfsWeekdaysIndex = i + monthDisplayHelper.getWeekStartDay();
            if (dfsWeekdaysIndex >= dfsWeekdays.length) {
                dfsWeekdaysIndex -= dfsWeekdays.length - 1;
            }
            weekdays[i] = dfsWeekdays[dfsWeekdaysIndex].substring(0, 2);
        }

        final int calendarHeaderY = CALENDAR_LEFT_Y + TEXT_SIZE_LARGE + 4;
        for (int i = 0; i < weekdays.length; i++) {
            drawText(Paint.Align.RIGHT, TEXT_SIZE_SMALL, weekdays[i].toUpperCase(), CALENDAR_LEFT_X + 16 +
                    (CALENDAR_CELL_WIDTH *
                            (i)), calendarHeaderY);
        }

        int tableY = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < weekdays.length; col++) {
                if (monthDisplayHelper.isWithinCurrentMonth(row, col)) {
                    int x = CALENDAR_LEFT_X + 16 + (CALENDAR_CELL_WIDTH * (col));
                    int y = calendarHeaderY + CALENDAR_CELL_HEIGHT * (row + 1);
                    int dayAt = monthDisplayHelper.getDayAt(row, col);
                    if (dayAt == mCalendar.get(Calendar.DAY_OF_MONTH)) {
                        mCanvas.drawRect(x + 4 - CALENDAR_CELL_WIDTH, y - 1, x + 4, y - 1 + CALENDAR_CELL_HEIGHT,
                                mTextPaint);
                        mTextPaint.setColor(Color.BLACK);
                    }
                    drawText(Paint.Align.RIGHT, TEXT_SIZE_SMALL,
                            String.format(Locale.getDefault(), "%02d", dayAt), x, y);
                    tableY = y + TEXT_SIZE_SMALL;
                    mTextPaint.setColor(Color.WHITE);
                }

            }
        }

        int agendaY = tableY + 8;
        drawText(Paint.Align.LEFT, TEXT_SIZE_MEDIUM, "Test", CALENDAR_LEFT_X, agendaY);
        for (int i = 0; i < 6; i++) {
            drawText(Paint.Align.LEFT, TEXT_SIZE_SMALL, "• Test " + i, CALENDAR_LEFT_X, agendaY + TEXT_SIZE_MEDIUM +
                    ((i) * TEXT_SIZE_SMALL));
        }
    }

    private void drawSensorData(SensorData sensorData) {
        int houseIconSide = drawDrawable(R.drawable.home, SENSORS_LEFT_X, SENSORS_LEFT_Y, 5);

        int firstColumnX = SENSORS_LEFT_X + houseIconSide;
        int secondRowY = SENSORS_LEFT_Y + houseIconSide;
        int sensorIconSide = drawDrawable(R.drawable.sensor_temperature, firstColumnX, SENSORS_LEFT_Y, 2);
        drawDrawable(R.drawable.sensor_humidity, firstColumnX, secondRowY, 2, false);

        drawText(Paint.Align.LEFT, TEXT_SIZE_MEDIUM_LARGE, Integer.toString(sensorData.getTemperature()) + "°",
                firstColumnX + sensorIconSide + 8, SENSORS_LEFT_Y - getTextCapsHeightFromTop(TEXT_SIZE_MEDIUM_LARGE)
                        / 2);
        int sensorTextWidth = drawText(Paint.Align.LEFT, TEXT_SIZE_MEDIUM_LARGE, Integer.toString(sensorData
                        .getHumidity()) + "%", firstColumnX + sensorIconSide + 8,
                secondRowY - getTextCapsHeightFromTop(TEXT_SIZE_MEDIUM_LARGE) / 2, false);

        int secondColumnX = firstColumnX + sensorTextWidth + sensorIconSide + 24;

        drawDrawable(R.drawable.sensor_pressure, secondColumnX, SENSORS_LEFT_Y, 2);
        drawDrawable(R.drawable.sensor_lux, secondColumnX, secondRowY, 2, false);

        drawText(Paint.Align.LEFT, TEXT_SIZE_MEDIUM_LARGE, Integer.toString(sensorData.getPressure()) + " hPa",
                secondColumnX + sensorIconSide + 16, SENSORS_LEFT_Y
                        - getTextCapsHeightFromTop(TEXT_SIZE_MEDIUM_LARGE) / 2);
        drawText(Paint.Align.LEFT, TEXT_SIZE_MEDIUM_LARGE, String.format(Locale.getDefault(), "%.1f lux", sensorData
                        .getLux()),
                secondColumnX + sensorIconSide + 16, secondRowY - getTextCapsHeightFromTop(TEXT_SIZE_MEDIUM_LARGE) /
                        2, false);
    }

    private void drawWeather(Weather[] weathers) {
        if (weathers != null && weathers.length > 0) {
            int todayIconSide = drawDrawable(weathers[0].getIcon(), WEATHER_LEFT_X, WEATHER_LEFT_Y, 5);
            int todayTemperatureWidth = drawText(Paint.Align.LEFT, TEXT_SIZE_MEDIUM_LARGE,
                    Integer.toString(weathers[0].getTemp()) + "°",
                    WEATHER_LEFT_X + 8 + todayIconSide, WEATHER_LEFT_Y + 2);

            CharSequence ellipsize = TextUtils.ellipsize(weathers[0].getConditions(), mTextPaint,
                    WEATHER_CONDITIONS_MAX_LENGTH, TextUtils.TruncateAt.END);

            drawText(Paint.Align.LEFT, TEXT_SIZE_MEDIUM, ellipsize.toString(), WEATHER_LEFT_X + 8 +
                    todayIconSide, WEATHER_LEFT_Y + todayIconSide - 10, false);
            for (int i = 1; i < weathers.length && i < FORECAST_DAYS + 1; i++) {
                int forecastIconY = WEATHER_LEFT_Y;
                int forecastIconSide = drawDrawable(weathers[i].getIcon(), WEATHER_LEFT_X + todayTemperatureWidth + 2
                        + (i *
                        FORECAST_CELL_WIDTH), forecastIconY, 3);
                drawText(Paint.Align.LEFT, TEXT_SIZE_SMALL, mDayOfWeekShortDateFormat.format(weathers[i].getDate())
                                .toUpperCase(), WEATHER_LEFT_X + todayTemperatureWidth + 2 + (i *
                                FORECAST_CELL_WIDTH) + 8 + forecastIconSide,
                        forecastIconY);
                drawText(Paint.Align.LEFT, TEXT_SIZE_SMALL, Integer.toString(weathers[i].getTempHigh()) + "°",
                        WEATHER_LEFT_X + todayTemperatureWidth + 2 + (i * FORECAST_CELL_WIDTH) + 8 +
                                forecastIconSide, forecastIconY +
                                forecastIconSide / 2 - getTextSizeBaseline(TEXT_SIZE_SMALL) / 2);
                drawText(Paint.Align.LEFT, TEXT_SIZE_SMALL, Integer.toString(weathers[i].getTempLow()) + "°",
                        WEATHER_LEFT_X + todayTemperatureWidth + 2 + (i * FORECAST_CELL_WIDTH) + 8 +
                                forecastIconSide, forecastIconY +
                                forecastIconSide, false);
            }
            //            drawText(Paint.Align.RIGHT, TEXT_SIZE_SMALLEST, "Powered by Weather Underground",
            //                    WEATHER_LEFT_X + todayTemperatureWidth + 8 + (
            //                            (weathers.length - 1) * FORECAST_CELL_WIDTH) + 78, WEATHER_LEFT_Y +
            // todayIconSide -
            //                            4);
        } else {
            drawText(Paint.Align.LEFT, TEXT_SIZE_MEDIUM, "Error fetching weather information", WEATHER_LEFT_X,
                    WEATHER_LEFT_Y + 9);
        }
    }

    private void drawFooter() {
        drawText(Paint.Align.CENTER, TEXT_SIZE_SMALL, getFooterText(), WIDTH / 2, HEIGHT - TEXT_PADDING, false);
    }

    private int drawDrawable(@DrawableRes int resId, int x, int y, int scale) {
        return drawDrawable(resId, x, y, scale, true);
    }

    private int drawDrawable(@DrawableRes int resId, int x, int y, int scale, boolean alignTop) {
        Drawable drawable = ContextCompat.getDrawable(mContext, resId);
        int width = drawable.getIntrinsicWidth() * scale;
        if (alignTop) {
            drawable.setBounds(x, y, x + width, y + drawable.getIntrinsicHeight() * scale);
        } else {
            drawable.setBounds(x, y - drawable.getIntrinsicHeight() * scale, x + width, y);
        }
        drawable.draw(mCanvas);
        return width;
    }

    private int drawText(Paint.Align align, int textSize, String text, int x, int y) {
        return drawText(align, textSize, text, x, y, true);
    }

    private int drawText(Paint.Align align, int textSize, String text, int x, int y, boolean alignTop) {
        mTextPaint.setTextAlign(align);
        mTextPaint.setTextSize(textSize);
        mCanvas.drawText(text, x, y + (alignTop ? getTextSizeBaseline(textSize) : 0), mTextPaint);
        return measureText(textSize, text);
    }

    private int measureText(int textSize, String text) {
        mTextPaint.setTextSize(textSize);
        return Math.round(mTextPaint.measureText(text));
    }

    private int getTextSizeBaseline(int textSize) {
        return Math.round(textSize / 8f * 7f);
    }

    private int getTextCapsHeightFromTop(int textSize) {
        return Math.round(textSize / 8f * 2f);
    }

    private String getCpuUsageText() {
        String usageString = "N/A";
        Integer cpuUsage = mSystemInfoHelper.getCpuUsage();
        if (cpuUsage != null) {
            usageString = Integer.toString(cpuUsage) + "%";
        }
        return String.format(Locale.getDefault(), "CPU %s", usageString);
    }

    private String getUptimeText() {
        return "UP " + mSystemInfoHelper.getUptime();
    }

    private String getLoadAvgText() {
        Float[] loadAvg = mSystemInfoHelper.getLoadAvg();
        return String.format(Locale.US, "LOAD AVG %.2f, %.2f, %.2f", loadAvg[0], loadAvg[1], loadAvg[2]);
    }

    private String getCpuTemperatureText() {
        String tempString = "N/A";
        Integer cpuTemp = mSystemInfoHelper.getCpuTemperature();
        if (cpuTemp != null) {
            tempString = Integer.toString(cpuTemp) + "°C";
        }
        return String.format(Locale.getDefault(), "CPU Temp %s", tempString);
    }

    private String getIpAddressText() {
        List<String> networkInterfaceAddresses = mSystemInfoHelper.getNetworkInterfaceAdresses();
        if (networkInterfaceAddresses.isEmpty()) {
            return "NOT CONNECTED";
        } else {
            return networkInterfaceAddresses.get(0);
        }
    }

    private String getFooterText() {
        return getUptimeText() + " | " + getLoadAvgText() + " | " + getCpuTemperatureText() + " | " +
                getIpAddressText();
    }

}
