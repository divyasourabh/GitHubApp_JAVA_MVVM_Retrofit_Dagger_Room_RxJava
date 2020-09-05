package com.ds.basicgithubapp.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.ds.basicgithubapp.R;
import com.ds.basicgithubapp.databinding.FragmentGithubRepoDetailBinding;
import com.ds.basicgithubapp.repo.api.model.GithubModel;
import com.ds.basicgithubapp.viewmodel.GithubRepoViewModel;

public class GithubRepoDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "position";
    private int position;
    private GithubRepoViewModel githubRepoViewModel;
    private FragmentGithubRepoDetailBinding fragmentGithubRepoDetailBinding;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public static GithubRepoDetailFragment newInstance(int position) {
        GithubRepoDetailFragment fragment = new GithubRepoDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        githubRepoViewModel = ViewModelProviders.of(getActivity()).get(GithubRepoViewModel.class);

        if (getArguments() != null) {
            position = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentGithubRepoDetailBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_github_repo_detail, container, false);

        return fragmentGithubRepoDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GithubModel githubModel = githubRepoViewModel.getGithubRepoLiveData().getValue().get(position);
        Glide.with(mContext)
                .load(githubModel.ownerModel.avatarUrl)
                .into(fragmentGithubRepoDetailBinding.imgAvatar);

        if (!TextUtils.isEmpty(githubModel.name)) {
            fragmentGithubRepoDetailBinding.tvName.setText(githubModel.name);
            fragmentGithubRepoDetailBinding.tvName.setVisibility(View.VISIBLE);
        } else {
            fragmentGithubRepoDetailBinding.tvName.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(githubModel.fullName)) {
            fragmentGithubRepoDetailBinding.tvFullName.setText(githubModel.fullName);
            fragmentGithubRepoDetailBinding.tvFullName.setVisibility(View.VISIBLE);
        } else {
            fragmentGithubRepoDetailBinding.tvFullName.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(githubModel.description)) {
            fragmentGithubRepoDetailBinding.tvDescription.setText(githubModel.description);
            fragmentGithubRepoDetailBinding.tvDescription.setVisibility(View.VISIBLE);
        } else {
            fragmentGithubRepoDetailBinding.tvDescription.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(githubModel.language)) {
            fragmentGithubRepoDetailBinding.tvLanguage.setText(githubModel.language);
            fragmentGithubRepoDetailBinding.tvLanguage.setVisibility(View.VISIBLE);
        } else {
            fragmentGithubRepoDetailBinding.tvLanguage.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(githubModel.htmlUrl)) {
            fragmentGithubRepoDetailBinding.tvHtmlUrl.setText(githubModel.htmlUrl);
            fragmentGithubRepoDetailBinding.tvHtmlUrl.setVisibility(View.VISIBLE);
        } else {
            fragmentGithubRepoDetailBinding.tvHtmlUrl.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(githubModel.teamUrl)) {
            fragmentGithubRepoDetailBinding.tvTeamUrl.setText(githubModel.teamUrl);
            fragmentGithubRepoDetailBinding.tvTeamUrl.setVisibility(View.VISIBLE);
        } else {
            fragmentGithubRepoDetailBinding.tvTeamUrl.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(githubModel.contributorsUrl)) {
            fragmentGithubRepoDetailBinding.tvContributorsUrl.setText(githubModel.contributorsUrl);
            fragmentGithubRepoDetailBinding.tvContributorsUrl.setVisibility(View.VISIBLE);
        } else {
            fragmentGithubRepoDetailBinding.tvContributorsUrl.setVisibility(View.GONE);
        }
    }
}