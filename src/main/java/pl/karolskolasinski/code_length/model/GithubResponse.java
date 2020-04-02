package pl.karolskolasinski.code_length.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubResponse {

    @SerializedName("")
    private List<UserRepos> userReposList;

    public List<UserRepos> getUserReposList() {
        return userReposList;
    }
}
