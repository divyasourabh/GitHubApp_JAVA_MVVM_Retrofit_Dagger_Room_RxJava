package com.ds.basicgithubapp.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.ds.basicgithubapp.R;
import com.ds.basicgithubapp.view.adapter.RepoListAdapter;
import com.ds.basicgithubapp.viewmodel.GithubRepoViewModel;
import com.ds.basicgithubapp.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class GithubActivity extends AppCompatActivity implements HasSupportFragmentInjector, RepoListAdapter.OnGitRepoClickListener {

    GithubRepoViewModel githubRepoViewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        githubRepoViewModel = ViewModelProviders.of(this,viewModelFactory).get(GithubRepoViewModel.class);
        attachFragment();
    }

    private void attachFragment () {
        Fragment githubRepoListFragment = new GithubRepoListFragment(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, githubRepoListFragment,"");
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(int position) {
        Log.d("Test1234", "Start Repo Detail fragment");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, GithubRepoDetailFragment.newInstance(position));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}