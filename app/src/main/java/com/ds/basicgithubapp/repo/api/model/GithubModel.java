package com.ds.basicgithubapp.repo.api.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class GithubModel {
    @SerializedName("name")
    public String name;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("team_url")
    public String teamUrl;
    @SerializedName("contributors_url")
    public String contributorsUrl;

    public GithubModel parseApiResponse(String apiResponse) {
        try {
            JSONObject jsonObject = new JSONObject(apiResponse);
            this.name = jsonObject.optString("name");
            this.fullName = jsonObject.optString("full_name");
            this.htmlUrl = jsonObject.optString("html_url");
            this.description = jsonObject.optString("description");
            this.url = jsonObject.optString("url");
            this.teamUrl = jsonObject.optString("team_url");
            this.contributorsUrl = jsonObject.optString("contributors_url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
