package com.ds.basicgithubapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.ds.basicgithubapp.R;
import com.ds.basicgithubapp.viewmodel.GithubRepoViewModel;

public class GithubActivity extends AppCompatActivity {

    GithubRepoViewModel githubRepoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        githubRepoViewModel = ViewModelProviders.of(this).get(GithubRepoViewModel.class);
        attachFragment();
    }

    private void attachFragment () {
        Fragment githubRepoListFragment = new GithubRepoListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, githubRepoListFragment,"");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}