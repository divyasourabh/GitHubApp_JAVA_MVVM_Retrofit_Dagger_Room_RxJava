package com.ds.basicgithubapp.dagger;

import com.ds.basicgithubapp.view.GithubRepoDetailFragment;
import com.ds.basicgithubapp.view.GithubRepoListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract GithubRepoListFragment contributeGithubRepoListFragment();

    @ContributesAndroidInjector
    abstract GithubRepoDetailFragment contributeGithubRepoDetailFragment();
}
