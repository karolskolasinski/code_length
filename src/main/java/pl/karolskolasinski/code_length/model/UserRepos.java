package pl.karolskolasinski.code_length.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class UserRepos {

    @SerializedName("id")
    private long id;

    @SerializedName("node_id")
    private String node_id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String full_name;

    @SerializedName("private")
    private boolean is_private;

    @SerializedName("owner")
    private Owner owner;

    @SerializedName("html_url")
    private String html_url;

    @SerializedName("description")
    private String description;

    @SerializedName("fork")
    private boolean fork;

    @SerializedName("url")
    private String url;

    @SerializedName("forks_url")
    private String forks_url;

    @SerializedName("keys_url")
    private String keys_url;

    @SerializedName("collaborators_url")
    private String collaborators_url;

    @SerializedName("teams_url")
    private String teams_url;

    @SerializedName("hooks_url")
    private String hooks_url;

    @SerializedName("issue_events_url")
    private String issue_events_url;

    @SerializedName("events_url")
    private String events_url;

    @SerializedName("assignees_url")
    private String assignees_url;

    @SerializedName("branches_url")
    private String branches_url;

    @SerializedName("tags_url")
    private String tags_url;

    @SerializedName("blobs_url")
    private String blobs_url;

    @SerializedName("git_tags_url")
    private String git_tags_url;

    @SerializedName("git_refs_url")
    private String git_refs_url;

    @SerializedName("trees_url")
    private String trees_url;

    @SerializedName("statuses_url")
    private String statuses_url;

    @SerializedName("languages_url")
    private String languages_url;

    @SerializedName("stargazers_url")
    private String stargazers_url;

    @SerializedName("contributors_url")
    private String contributors_url;

    @SerializedName("subscribers_url")
    private String subscribers_url;

    @SerializedName("subscription_url")
    private String subscription_url;

    @SerializedName("commits_url")
    private String commits_url;

    @SerializedName("git_commits_url")
    private String git_commits_url;

    @SerializedName("comments_url")
    private String comments_url;

    @SerializedName("issue_comment_url")
    private String issue_comment_url;

    @SerializedName("contents_url")
    private String contents_url;

    @SerializedName("compare_url")
    private String compare_url;

    @SerializedName("merges_url")
    private String merges_url;

    @SerializedName("archive_url")
    private String archive_url;

    @SerializedName("downloads_url")
    private String downloads_url;

    @SerializedName("issues_url")
    private String issues_url;

    @SerializedName("pulls_url")
    private String pulls_url;

    @SerializedName("milestones_url")
    private String milestones_url;

    @SerializedName("notifications_url")
    private String notifications_url;

    @SerializedName("labels_url")
    private String labels_url;

    @SerializedName("releases_url")
    private String releases_url;

    @SerializedName("deployments_url")
    private String deployments_url;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("pushed_at")
    private String pushed_at;

    @SerializedName("git_url")
    private String git_url;

    @SerializedName("ssh_url")
    private String ssh_url;

    @SerializedName("clone_url")
    private String clone_url;

    @SerializedName("svn_url")
    private String svn_url;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("size")
    private int size;

    @SerializedName("stargazers_count")
    private int stargazers_count;

    @SerializedName("watchers_count")
    private int watchers_count;

    @SerializedName("language")
    private String language;

    @SerializedName("has_issues")
    private boolean has_issues;

    @SerializedName("has_projects")
    private boolean has_projects;

    @SerializedName("has_downloads")
    private boolean has_downloads;

    @SerializedName("has_wiki")
    private boolean has_wiki;

    @SerializedName("has_pages")
    private boolean has_pages;

    @SerializedName("forks_count")
    private int forks_count;

    @SerializedName("mirror_url")
    private String mirror_url;

    @SerializedName("archived")
    private boolean archived;

    @SerializedName("disabled")
    private boolean disabled;

    @SerializedName("open_issues_count")
    private int open_issues_count;

    @SerializedName("license")
    private License license;

    @SerializedName("forks")
    private int forks;

    @SerializedName("open_issues")
    private int open_issues;

    @SerializedName("watchers")
    private int watchers;

    @SerializedName("default_branch")
    private String default_branch;
}
