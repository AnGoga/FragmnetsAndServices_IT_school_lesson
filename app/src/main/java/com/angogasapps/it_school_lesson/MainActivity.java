package com.angogasapps.it_school_lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    BottomNavigationView bottomNavigation;
    SomeFragment fragmentHome = new SomeFragment("Home");
    SomeFragment fragmentWork = new SomeFragment("Work");
    SomeFragment fragmentSettings = new SomeFragment("Settings");
    SomeService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.container);
        bottomNavigation = findViewById(R.id.bottomNav);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragmentHome)
                .commit();

        initService();


        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homeId) {
//                    System.out.println("homeId");
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, fragmentHome)
                            .commit();
                } else if (item.getItemId() == R.id.workId) {
//                    System.out.println("workId");
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, fragmentWork)
                            .commit();
                } else if (item.getItemId() == R.id.settingsId) {
//                    System.out.println("settingsId");
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, fragmentSettings)
                            .commit();

                }
                return true;
            }
        });


    }

    private void initService() {
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                SomeService.MyServiceBinder binder = (SomeService.MyServiceBinder)service;
                mService = binder.getService();
                doWorkByService();
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        Intent intent = new Intent(this, SomeService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);


    }

    private void doWorkByService() {
        List<Integer> list = mService.getDataFromDatabase();
        System.out.println(list);
    }
}