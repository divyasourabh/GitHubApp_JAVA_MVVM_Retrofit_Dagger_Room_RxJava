package com.ds.basicgithubapp.repo.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GithubDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertGithubRepos(List<GithubEntity> githubRepoEntityList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertGithubRepo(GithubEntity githubRepoEntity);

    @Query("SELECT * FROM github_repo")
    LiveData<List<GithubEntity>> loadGithubRepoList();

}

