package com.ds.basicgithubapp.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ds.basicgithubapp.repo.api.retrofit.GetRepoService;
import com.ds.basicgithubapp.repo.api.retrofit.RetrofitClientInstance;
import com.ds.basicgithubapp.repo.room.GithubDAO;
import com.ds.basicgithubapp.repo.room.GithubEntity;
import com.ds.basicgithubapp.repo.room.GithubRoomDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubRepository {

    public static String GITHUB_USERNAME = "divyasourabh";
    private static GithubRepository githubRepositoryInstance;

    private GetRepoService getRepoService;

    //ROOOM
    private GithubDAO githubDAO;

    private LiveData<List<GithubEntity>> githubListLiveData;

    public LiveData<List<GithubEntity>> getGithubListLiveData() {
        return githubListLiveData;
    }

    GithubRepository(Application application) {
        GithubRoomDatabase db = GithubRoomDatabase.getDatabase(application);
        githubDAO = db.githubDAO();
        githubListLiveData = githubDAO.loadGithubRepoList();
        getRepoService = RetrofitClientInstance.getRetrofitInstance().create(GetRepoService.class);
    }

    public void insert(List<GithubEntity> githubModelList) {
        GithubRoomDatabase.databaseWriteExecutor.execute(() -> {
            githubDAO.insertGithubRepos(githubModelList);
        });
    }

    public void insertRepo(GithubEntity githubModel) {
        GithubRoomDatabase.databaseWriteExecutor.execute(() -> {
            githubDAO.insertGithubRepo(githubModel);
        });
    }

    public static GithubRepository getInstance(Application application) {
        if (githubRepositoryInstance == null) {
            githubRepositoryInstance = new GithubRepository(application);
        }

        return githubRepositoryInstance;
    }

    public MutableLiveData<List<GithubEntity>> fetchRepoListFromAPI () {


        Call<List<GithubEntity>> call = getRepoService.fetchRepos(GITHUB_USERNAME);
        MutableLiveData<List<GithubEntity>> githubRepoLiveData = new MutableLiveData<>();
        call.enqueue(new Callback<List<GithubEntity>>() {
            @Override
            public void onResponse(Call<List<GithubEntity>> call, Response<List<GithubEntity>> response) {
                githubRepoLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubEntity>> call, Throwable t) {
                Log.d("Test123","Something went wrong...Please try later!");
            }
        });
        return githubRepoLiveData;
    }


    public void updateDBFromAPI () {

        GetRepoService getRepoService = RetrofitClientInstance.getRetrofitInstance().create(GetRepoService.class);
        Call<List<GithubEntity>> call = getRepoService.fetchReposFromAPI(GITHUB_USERNAME);
        call.enqueue(new Callback<List<GithubEntity>>() {
            @Override
            public void onResponse(Call<List<GithubEntity>> call, Response<List<GithubEntity>> response) {
                insert(response.body());
//                insertRepo(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<GithubEntity>> call, Throwable t) {
                Log.d("Test123","Something went wrong...Please try later!");
            }
        });
    }
}
