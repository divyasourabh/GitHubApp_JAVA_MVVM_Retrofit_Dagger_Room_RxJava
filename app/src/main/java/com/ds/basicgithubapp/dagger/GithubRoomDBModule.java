package com.ds.basicgithubapp.dagger;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.ds.basicgithubapp.repo.room.GithubDAO;
import com.ds.basicgithubapp.repo.room.GithubRoomDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GithubRoomDBModule {

    @Provides
    @Singleton
    GithubRoomDatabase provideGithubRoomDatabase (@NonNull Application application) {
        return Room.databaseBuilder(application,
                GithubRoomDatabase.class, "github_database")
                .build();
    }

    @Provides
    @Singleton
    GithubDAO provideGithubDAO (GithubRoomDatabase githubRoomDatabase) {
        return githubRoomDatabase.githubDAO();
    }
}
