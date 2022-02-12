package com.angogasapps.it_school_lesson;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SomeService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        System.out.println("startId = " + startId);
//        doWork();
        return START_REDELIVER_INTENT;
    }

    public List<Integer> getDataFromDatabase() {
        List<Integer> list = new ArrayList();
        list.add(6);
        list.add(124);
        list.add(-140);
        list.add(235);
        return list;
    }

    private void doWork() {
        (new Thread(){
            @Override
            public void run() {
                int a = 0;

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Привет из СЕРВИСА -> " + a++);
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind");
        return super.onUnbind(intent);
    }


    @Override
    public void onRebind(Intent intent) {
        System.out.println("onRebind");
        super.onRebind(intent);
    }

    public class MyServiceBinder extends Binder {
        public SomeService getService() {
            return SomeService.this;
        }
    }
}