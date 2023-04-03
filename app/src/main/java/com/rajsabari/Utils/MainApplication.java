package com.rajsabari.Utils;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration value = new RealmConfiguration.Builder()
                .name("value")
                .schemaVersion(1)
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();


        Realm.setDefaultConfiguration(value);
    }
}
