package com.ds.basicgithubapp.dagger;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ds.basicgithubapp.viewmodel.GithubRepoViewModel;
import com.ds.basicgithubapp.viewmodel.ViewModelFactory;
import com.ds.basicgithubapp.viewmodel.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(GithubRepoViewModel.class)
    protected abstract ViewModel movieListViewModel(GithubRepoViewModel githubRepoViewModel);
}
