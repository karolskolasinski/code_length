package pl.karolskolasinski.code_length.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class UserRepos {

    @SerializedName("id")
    private long id;


    @SerializedName("node_id")
    private String nodeId;


    @SerializedName("name")
    private String name;


    @SerializedName("full_name")
    private String fullName;


    @SerializedName("private")
    private boolean isPrivate;


    @SerializedName("owner")
    private Owner owner;


    @SerializedName("html_url")
    private String htmlURL;


    @SerializedName("description")
    private String description;


    @SerializedName("fork")
    private boolean fork;


    @SerializedName("url")
    private String URL;


    @SerializedName("forks_url")
    private String forksURL;


    @SerializedName("keys_url")
    private String keysURL;


    @SerializedName("collaborators_url")
    private String collaboratorsURL;


    @SerializedName("teams_url")
    private String teamsURL;


    @SerializedName("hooks_url")
    private String hooksURL;


    @SerializedName("issue_events_url")
    private String issueEventsURL;


    @SerializedName("events_url")
    private String eventsURL;


    @SerializedName("assignees_url")
    private String assigneesURL;


    @SerializedName("branches_url")
    private String branchesURL;


    @SerializedName("tags_url")
    private String tagsURL;


    @SerializedName("blobs_url")
    private String blobsURL;


    @SerializedName("git_tags_url")
    private String gitTagsURL;


    @SerializedName("git_refs_url")
    private String gitRefsURL;


    @SerializedName("trees_url")
    private String treesURL;


    @SerializedName("statuses_url")
    private String statuses_URL;


    @SerializedName("languages_url")
    private String languagesURL;


    @SerializedName("stargazers_url")
    private String stargazersURL;


    @SerializedName("contributors_url")
    private String contributorsURL;


    @SerializedName("subscribers_url")
    private String subscribersURL;


    @SerializedName("subscription_url")
    private String subscriptionURL;


    @SerializedName("commits_url")
    private String commitsURL;


    @SerializedName("git_commits_url")
    private String gitCommitsURL;


    @SerializedName("comments_url")
    private String commentsURL;


    @SerializedName("issue_comment_url")
    private String issueCommentURL;


    @SerializedName("contents_url")
    private String contentsURL;


    @SerializedName("compare_url")
    private String compareURL;


    @SerializedName("merges_url")
    private String mergesURL;


    @SerializedName("archive_url")
    private String archiveURL;


    @SerializedName("downloads_url")
    private String downloadsURL;


    @SerializedName("issues_url")
    private String issuesURL;


    @SerializedName("pulls_url")
    private String pullsURL;


    @SerializedName("milestones_url")
    private String milestonesURL;


    @SerializedName("notifications_url")
    private String notificationsURL;


    @SerializedName("labels_url")
    private String labelsURL;


    @SerializedName("releases_url")
    private String releasesURL;


    @SerializedName("deployments_url")
    private String deploymentsURL;


    @SerializedName("created_at")
    private String createdAt;


    @SerializedName("updated_at")
    private String updatedAt;


    @SerializedName("pushed_at")
    private String pushedAt;


    @SerializedName("git_url")
    private String gitURL;


    @SerializedName("ssh_url")
    private String sshURL;


    @SerializedName("clone_url")
    private String cloneURL;


    @SerializedName("svn_url")
    private String svnURL;


    @SerializedName("homepage")
    private String homepage;


    @SerializedName("size")
    private int size;


    @SerializedName("stargazers_count")
    private int stargazersCount;


    @SerializedName("watchers_count")
    private int watchersCount;


    @SerializedName("language")
    private String language;


    @SerializedName("has_issues")
    private boolean hasIssues;


    @SerializedName("has_projects")
    private boolean hasProjects;


    @SerializedName("has_downloads")
    private boolean hasDownloads;


    @SerializedName("has_wiki")
    private boolean hasWiki;


    @SerializedName("has_pages")
    private boolean hasPages;


    @SerializedName("forks_count")
    private int forksCount;


    @SerializedName("mirror_url")
    private String mirrorURL;


    @SerializedName("archived")
    private boolean archived;


    @SerializedName("disabled")
    private boolean disabled;


    @SerializedName("open_issues_count")
    private int openIssuesCount;


    @SerializedName("license")
    private License license;


    @SerializedName("forks")
    private int forks;


    @SerializedName("open_issues")
    private int openIssues;


    @SerializedName("watchers")
    private int watchers;


    @SerializedName("default_branch")
    private String defaultBranch;

}
