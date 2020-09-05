package com.ds.basicgithubapp.repo;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.ds.basicgithubapp.repo.api.model.GithubModel;
import com.ds.basicgithubapp.repo.api.retrofit.GetRepoService;
import com.ds.basicgithubapp.repo.api.retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkUtils {

    public static String GITHUB_USERNAME = "divyasourabh";
    private static NetworkUtils networkUtilsInstance;

    public static NetworkUtils getInstance() {
        if (networkUtilsInstance == null) {
            networkUtilsInstance = new NetworkUtils();
        }

        return networkUtilsInstance;
    }

    public MutableLiveData<List<GithubModel>> fetchRepoListFromAPI () {

        GetRepoService getRepoService = RetrofitClientInstance.getRetrofitInstance().create(GetRepoService.class);
        Call<List<GithubModel>> call = getRepoService.fetchRepos(GITHUB_USERNAME);
        MutableLiveData<List<GithubModel>> githubRepoLiveData = new MutableLiveData<>();
        call.enqueue(new Callback<List<GithubModel>>() {
            @Override
            public void onResponse(Call<List<GithubModel>> call, Response<List<GithubModel>> response) {
                Log.d("Test123",response.body().toString());

                githubRepoLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubModel>> call, Throwable t) {
                Log.d("Test123","Something went wrong...Please try later!");
            }
        });
        return githubRepoLiveData;
    }
}
