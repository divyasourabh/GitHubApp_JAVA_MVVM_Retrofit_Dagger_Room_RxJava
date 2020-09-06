package com.ds.basicgithubapp.repo.room;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {GithubEntity.class}, version = 1, exportSchema = false)
public abstract class GithubRoomDatabase extends RoomDatabase {

    public abstract GithubDAO githubDAO();

    private static volatile GithubRoomDatabase githubRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static GithubRoomDatabase getDatabase(Application application) {
        if (githubRoomDatabase == null) {
            synchronized (GithubRoomDatabase.class) {
                if (githubRoomDatabase == null) {
                    githubRoomDatabase = Room.databaseBuilder(application,
                            GithubRoomDatabase.class, "github_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return githubRoomDatabase;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
