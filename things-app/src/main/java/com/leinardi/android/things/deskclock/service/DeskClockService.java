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

package com.leinardi.android.things.deskclock.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.leinardi.android.things.deskclock.R;
import com.leinardi.android.things.deskclock.config.ConfigRepository;
import com.leinardi.android.things.deskclock.controller.DeskClockController;
import com.leinardi.android.things.deskclock.ui.MainActivity;
import dagger.android.AndroidInjection;
import timber.log.Timber;

import javax.inject.Inject;

public class DeskClockService extends Service {
    public static final String ACTION_START_DESK_CLOCK_SERVICE
            = "com.leinardi.android.things.intent.action.START_DESK_CLOCK_SERVICE";
    public static final String ACTION_STOP_DESK_CLOCK_SERVICE
            = "com.leinardi.android.things.intent.action.STOP_DESK_CLOCK_SERVICE";
    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private static final int NOTIFICATION = R.string.deskclock_service_started;
    private boolean mIsInForeground;

    @Inject
    DeskClockController mDeskClockController;
    @Inject
    ConfigRepository mConfigRepository;

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        public DeskClockService getService() {
            return DeskClockService.this;
        }
    }

    @Override
    public void onCreate() {
        Timber.d("DeskClock Service started");
        AndroidInjection.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (ACTION_START_DESK_CLOCK_SERVICE.equals(intent.getAction())) {
                Timber.d("Received Start Foreground Intent ");
                if (!mIsInForeground) {
                    showNotification();
                    mIsInForeground = true;
                    mDeskClockController.setLocale(mConfigRepository.getLocale());
                    mDeskClockController.setTimeZone(mConfigRepository.getTimeZone());

                    mDeskClockController.start();
                }
            } else if (ACTION_STOP_DESK_CLOCK_SERVICE.equals(intent.getAction())) {
                Timber.d("Received Stop Foreground Intent");
                mDeskClockController.close();
                stopSelf();
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Timber.d("DeskClock Service stopped");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    /**
     * Show a notification while this service is running.
     */

    private void showNotification() {
        CharSequence text = getText(R.string.deskclock_service_started);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        String channelId = createNotificationChannel();
        // Set the info for the views that show in the notification panel.
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_stat_service)
                .setTicker(text)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle(getText(R.string.app_name))  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .build();

        // Send the notification.
        startForeground(NOTIFICATION, notification);
        Timber.d("startForeground()");
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        String channelId = "deskclock";
        String channelName = getString(R.string.app_name) + " Background Service";
        NotificationChannel chan = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_HIGH);
        chan.setLightColor(Color.BLUE);
        chan.setImportance(NotificationManager.IMPORTANCE_NONE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager service = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        service.createNotificationChannel(chan);
        return channelId;
    }
}
