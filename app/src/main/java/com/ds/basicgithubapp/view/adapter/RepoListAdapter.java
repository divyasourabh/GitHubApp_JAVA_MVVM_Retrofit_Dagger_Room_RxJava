package com.ds.basicgithubapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ds.basicgithubapp.R;
import com.ds.basicgithubapp.databinding.GithubRepoListItemBinding;
import com.ds.basicgithubapp.repo.room.GithubEntity;

import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    protected List<GithubEntity> githubModelList;
    protected Context mContext;
    private OnGitRepoClickListener onGitRepoClickListener;

    public RepoListAdapter(Context context, List<GithubEntity> githubModelList, OnGitRepoClickListener onGitRepoClickListener) {
        this.githubModelList = githubModelList;
        mContext = context;
        this.onGitRepoClickListener = onGitRepoClickListener;
    }

    public void setList(List<GithubEntity> githubModelList) {
//        this.githubModelList.clear();
        this.githubModelList.addAll(githubModelList);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GithubRepoListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.github_repo_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoListAdapter.ViewHolder holder, int position) {
        GithubEntity githubModel = githubModelList.get(position);
        holder.bind(githubModel, onGitRepoClickListener);
    }

    @Override
    public int getItemCount() {
        if (githubModelList != null) {
            return githubModelList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public GithubRepoListItemBinding itemRowBinding;
        OnGitRepoClickListener onGitRepoClickListener;

        public void bind(GithubEntity githubModel, OnGitRepoClickListener onGitRepoClickListener) {
            this.onGitRepoClickListener = onGitRepoClickListener;
            Glide.with(mContext)
                    .load(githubModel.ownerEntity.avatarUrl)
                    .into(itemRowBinding.imgAvatar);
            itemRowBinding.tvFullName.setText(githubModel.fullName);
            itemRowBinding.tvReponame.setText(githubModel.name);
            itemRowBinding.tvLanguage.setText(githubModel.language);
        }

        public ViewHolder(GithubRepoListItemBinding binding) {
            super(binding.getRoot());
            itemRowBinding = binding;

            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onGitRepoClickListener.onClick(getAdapterPosition());
        }
    }

    public interface OnGitRepoClickListener {
        void onClick (int position);
    }
}
