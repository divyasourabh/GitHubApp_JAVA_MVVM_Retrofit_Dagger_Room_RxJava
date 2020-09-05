package com.ds.basicgithubapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ds.basicgithubapp.repo.NetworkUtils;
import com.ds.basicgithubapp.repo.api.model.GithubModel;

import java.util.List;

public class GithubRepoViewModel extends AndroidViewModel {

    private MutableLiveData<List<GithubModel>> githubRepoLiveData;

    public GithubRepoViewModel(@NonNull Application application) {
        super(application);
        githubRepoLiveData = new MutableLiveData<>();
    }


    public LiveData<List<GithubModel>> getGithubRepoLiveData() {
        return githubRepoLiveData;
    }

    public void loadData () {
        githubRepoLiveData = NetworkUtils.getInstance().fetchRepoListFromAPI();
    }
}
