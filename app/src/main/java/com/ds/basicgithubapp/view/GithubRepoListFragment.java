package com.ds.basicgithubapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ds.basicgithubapp.R;
import com.ds.basicgithubapp.databinding.FragmentGithubRepoListBinding;
import com.ds.basicgithubapp.repo.api.model.GithubModel;
import com.ds.basicgithubapp.view.adapter.RepoListAdapter;
import com.ds.basicgithubapp.viewmodel.GithubRepoViewModel;

import java.util.ArrayList;
import java.util.List;

public class GithubRepoListFragment extends Fragment {

    private GithubRepoViewModel githubRepoViewModel;
    private FragmentGithubRepoListBinding fragmentGithubRepoListBinding;
    private RepoListAdapter repoListAdapter;
    private RepoListAdapter.OnGitRepoClickListener onGitRepoClickListener;

    public GithubRepoListFragment(RepoListAdapter.OnGitRepoClickListener onGitRepoClickListener) {
        this.onGitRepoClickListener = onGitRepoClickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        githubRepoViewModel = ViewModelProviders.of(getActivity()).get(GithubRepoViewModel.class);

        if (getArguments() != null) {
        }

        githubRepoViewModel.loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentGithubRepoListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_github_repo_list, container, false);

        return fragmentGithubRepoListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentGithubRepoListBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        repoListAdapter = new RepoListAdapter(getActivity(),new ArrayList<>(),onGitRepoClickListener);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        fragmentGithubRepoListBinding.recyclerview.setHasFixedSize(true);
        fragmentGithubRepoListBinding.recyclerview.addItemDecoration(dividerItemDecoration);
        fragmentGithubRepoListBinding.recyclerview.setAdapter(repoListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        githubRepoViewModel.getGithubRepoLiveData().observe(this, new Observer<List<GithubModel>>() {
            @Override
            public void onChanged(List<GithubModel> githubModelList) {
                repoListAdapter.setList(githubModelList);
            }
        });
    }
}