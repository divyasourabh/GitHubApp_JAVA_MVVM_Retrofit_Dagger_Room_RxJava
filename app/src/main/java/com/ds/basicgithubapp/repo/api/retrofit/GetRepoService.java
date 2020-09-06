package com.ds.basicgithubapp.repo.api.retrofit;

import com.ds.basicgithubapp.repo.room.GithubEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetRepoService {

    @GET("/users/{user}/repos")
    Call<List<GithubEntity>> fetchRepos(@Path("user") String user);

    @GET("/users/{user}/repos")
    Call<List<GithubEntity>> fetchReposFromAPI(@Path("user") String user);
}