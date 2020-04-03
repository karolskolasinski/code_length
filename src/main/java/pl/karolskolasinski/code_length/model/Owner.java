package pl.karolskolasinski.code_length.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;

@Getter
public class Owner {

    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private String id;

    @SerializedName("node_id")
    private String node_id;

    @SerializedName("avatar_url")
    private String avatar_url;

    @SerializedName("gravatar_id")
    private String gravatar_id;

    @SerializedName("url")
    private String url;

    @SerializedName("html_url")
    private String html_url;

    @SerializedName("followers_url")
    private String followers_url;

    @SerializedName("following_url")
    private String following_url;

    @SerializedName("gists_url")
    private String gists_url;

    @SerializedName("starred_url")
    private String starred_url;

    @SerializedName("subscriptions_url")
    private String subscriptions_url;

    @SerializedName("organizations_url")
    private String organizations_url;

    @SerializedName("repos_url")
    private String repos_url;

    @SerializedName("events_url")
    private String events_url;

    @SerializedName("received_events_url")
    private String received_events_url;

    @SerializedName("type")
    private String type;

    @SerializedName("site_admin")
    private String site_admin;
}
