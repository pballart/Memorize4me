package com.best.memorize4me;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.best.memorize4me.db.DbHelper;

import java.util.logging.Logger;

/**
 * Created by konstantin.bogdanov on 07.07.2015.
 */
public class MemorizeForMeApplication extends Application {
    private volatile DbHelper dbHelper;
    private static MemorizeForMeApplication applicationContext;

    private SQLiteOpenHelper getDBHelper() {
        return dbHelper;
    }

    private void initDatabase() {
        dbHelper = new DbHelper(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDatabase();
        applicationContext = this;
    }

    public static @Nullable
    SQLiteDatabase getDatabase(Context context) {
        return ((MemorizeForMeApplication)context.getApplicationContext()).getDatabase();
    }

    private @Nullable SQLiteDatabase getDatabase() {
        try {
            return getDBHelper().getWritableDatabase();
        } catch (Exception e) {
        }

        return null;
    }

    public static Context getContext() {
        return applicationContext;
    }

}
