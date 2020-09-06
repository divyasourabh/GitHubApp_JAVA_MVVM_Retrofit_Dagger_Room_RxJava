package com.ds.basicgithubapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ds.basicgithubapp.repo.GithubRepository;
import com.ds.basicgithubapp.repo.api.retrofit.GithubRepoService;
import com.ds.basicgithubapp.repo.room.GithubDAO;
import com.ds.basicgithubapp.repo.room.GithubEntity;

import java.util.List;

import javax.inject.Inject;

public class GithubRepoViewModel extends AndroidViewModel {

    private GithubRepository githubRepositoryInstance;

    private LiveData<List<GithubEntity>> githubRepoEntityLiveData;

    @Inject
    public GithubRepoViewModel(@NonNull Application application, GithubDAO githubDAO, GithubRepoService getRepoService) {
        super(application);
        githubRepositoryInstance = GithubRepository.getInstance(application, githubDAO, getRepoService);
        githubRepoEntityLiveData = githubRepositoryInstance.getGithubListLiveData();
    }

    public LiveData<List<GithubEntity>> getGithubRepoEntityLiveData() {
        return githubRepoEntityLiveData;
    }

    public void loadGithubRepoList() {
        if (githubRepositoryInstance != null) {
            if (githubRepositoryInstance.getGithubListLiveData() != null && githubRepositoryInstance.getGithubListLiveData().getValue() != null) {
                githubRepoEntityLiveData = githubRepositoryInstance.getGithubListLiveData();
            } else {
                githubRepositoryInstance.updateDBFromAPI();
            }
        }
    }
}
