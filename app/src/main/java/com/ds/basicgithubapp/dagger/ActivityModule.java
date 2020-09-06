package com.ds.basicgithubapp.dagger;

import com.ds.basicgithubapp.view.GithubActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract GithubActivity contributeGithubActivity();
}
