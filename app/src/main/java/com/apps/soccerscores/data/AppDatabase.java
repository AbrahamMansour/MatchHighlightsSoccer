package com.apps.soccerscores.data;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import androidx.annotation.NonNull;

import com.apps.soccerscores.workers.SeedDatabaseWorker;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

@Database(entities = {Match.class, Favourites.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MatchDao matchDao();
    public abstract FavouritesDao favouritesDao();
    private static AppDatabase instance = null;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null)
                    instance = buildDatabase(context);
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "soccerscores-db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SeedDatabaseWorker.class).build();
                        WorkManager.getInstance().enqueue(workRequest);
                    }
                }).build();
    }

    public static void destroyDatabase() {
        instance = null;
    }
}
