package com.ds.basicgithubapp.repo.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity
public class OwnerEntity {

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    public String avatarUrl;
}
