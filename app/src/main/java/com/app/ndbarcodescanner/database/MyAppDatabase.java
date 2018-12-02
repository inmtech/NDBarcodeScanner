package com.app.ndbarcodescanner.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Ticket.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {
    public static MyAppDatabase INSTANCE;

    public abstract DaoNote daoNote();

    public static MyAppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, MyAppDatabase.class, "barcodeticket").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
