package pl.karolskolasinski.code_length.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class User {

    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private int id;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("avatar_url")
    private String avatarURL;

    @SerializedName("gravatar_id")
    private String gravatarId;

    @SerializedName("url")
    private String URL;

    @SerializedName("html_url")
    private String htmlURL;

    @SerializedName("followers_url")
    private String followersURL;

    @SerializedName("following_url")
    private String followingURL;

    @SerializedName("gists_url")
    private String gistsURL;

    @SerializedName("starred_url")
    private String starredURL;

    @SerializedName("subscriptions_url")
    private String subscriptionsURL;

    @SerializedName("organizations_url")
    private String organizationsURL;

    @SerializedName("repos_url")
    private String reposURL;

    @SerializedName("events_url")
    private String eventsURL;

    @SerializedName("received_events_url")
    private String receivedEventsURL;

    @SerializedName("type")
    private String type;

    @SerializedName("site_admin")
    private boolean siteAdmin;

    @SerializedName("name")
    private String name;

    @SerializedName("company")
    private String company;

    @SerializedName("blog")
    private String blog;

    @SerializedName("location")
    private String location;

    @SerializedName("email")
    private String email;

    @SerializedName("hireable")
    private boolean hireable;

    @SerializedName("bio")
    private String bio;

    @SerializedName("public_repos")
    private int publicRepos;

    @SerializedName("public_gists")
    private int publicGists;

    @SerializedName("followers")
    private int followers;

    @SerializedName("following")
    private int following;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

}
