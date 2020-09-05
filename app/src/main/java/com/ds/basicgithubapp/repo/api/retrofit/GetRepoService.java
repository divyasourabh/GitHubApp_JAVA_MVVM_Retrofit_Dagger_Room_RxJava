package com.ds.basicgithubapp.repo.api.retrofit;

import com.ds.basicgithubapp.repo.api.model.GithubModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetRepoService {

    @GET("/users/{user}/repos")
    Call<List<GithubModel>> fetchRepos(@Path("user") String user);
}