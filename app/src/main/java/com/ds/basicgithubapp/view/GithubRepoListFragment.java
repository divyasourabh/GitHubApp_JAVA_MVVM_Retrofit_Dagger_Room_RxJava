package com.ds.basicgithubapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ds.basicgithubapp.R;
import com.ds.basicgithubapp.repo.api.model.GithubModel;
import com.ds.basicgithubapp.viewmodel.GithubRepoViewModel;

import java.util.List;

public class GithubRepoListFragment extends Fragment {

    private GithubRepoViewModel githubRepoViewModel;

    public GithubRepoListFragment() {
        // Required empty public constructor
    }

    public static GithubRepoListFragment newInstance(String param1, String param2) {
        GithubRepoListFragment fragment = new GithubRepoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        githubRepoViewModel = ViewModelProviders.of(this).get(GithubRepoViewModel.class);

        if (getArguments() != null) {
        }

        githubRepoViewModel.loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_github_repo_list, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        githubRepoViewModel.getGithubRepoLiveData().observe(this, new Observer<List<GithubModel>>() {
            @Override
            public void onChanged(List<GithubModel> githubModels) {
                Log.d("Test123","Livedata on Fragment");
            }
        });
    }
}