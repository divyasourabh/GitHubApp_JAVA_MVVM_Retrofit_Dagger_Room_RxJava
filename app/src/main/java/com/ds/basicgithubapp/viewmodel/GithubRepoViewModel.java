package com.ds.basicgithubapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ds.basicgithubapp.repo.GithubRepository;
import com.ds.basicgithubapp.repo.room.GithubEntity;

import java.util.List;

public class GithubRepoViewModel extends AndroidViewModel {

//    private MutableLiveData<List<GithubModel>> githubRepoLiveData;
    private GithubRepository githubRepositoryInstance;

    private LiveData<List<GithubEntity>> githubRepoEntityLiveData;

    public GithubRepoViewModel(@NonNull Application application) {
        super(application);
//        githubRepoLiveData = new MutableLiveData<>();
        githubRepositoryInstance = GithubRepository.getInstance(getApplication());
        githubRepoEntityLiveData = githubRepositoryInstance.getGithubListLiveData();
    }

//    public LiveData<List<GithubModel>> getGithubRepoLiveData() {
//        return githubRepoLiveData;
//    }

    public LiveData<List<GithubEntity>> getGithubRepoEntityLiveData() {
        return githubRepoEntityLiveData;
    }

//    public void loadData () {
//        githubRepoLiveData = githubRepositoryInstance.fetchRepoListFromAPI();
//    }


    public void loadDataFromRepoLayer () {
//        githubRepoEntityLiveData = githubRepositoryInstance.fetchRepoListFromAPI();

        if (githubRepositoryInstance != null) {
            if (githubRepositoryInstance.getGithubListLiveData() != null && githubRepositoryInstance.getGithubListLiveData().getValue() != null) {
                githubRepoEntityLiveData = githubRepositoryInstance.getGithubListLiveData();
            } else {
                githubRepositoryInstance.updateDBFromAPI();
            }
        }
    }
}
