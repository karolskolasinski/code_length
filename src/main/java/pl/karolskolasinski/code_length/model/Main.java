package pl.karolskolasinski.code_length.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws IOException {


        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        String val1 = "[\n" +
                "  {\n" +
                "    \"id\": 198218952,\n" +
                "    \"node_id\": \"MDEwOlJlcG9zaXRvcnkxOTgyMTg5NTI=\",\n" +
                "    \"name\": \"api_json_recipepuppy\",\n" +
                "    \"full_name\": \"karolskolasinski/api_json_recipepuppy\",\n" +
                "    \"private\": false,\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"karolskolasinski\",\n" +
                "      \"id\": 43741017,\n" +
                "      \"node_id\": \"MDQ6VXNlcjQzNzQxMDE3\",\n" +
                "      \"avatar_url\": \"https://avatars0.githubusercontent.com/u/43741017?v=4\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/karolskolasinski\",\n" +
                "      \"html_url\": \"https://github.com/karolskolasinski\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/karolskolasinski/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/karolskolasinski/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/karolskolasinski/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/karolskolasinski/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/karolskolasinski/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/karolskolasinski/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/karolskolasinski/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/karolskolasinski/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/karolskolasinski/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"html_url\": \"https://github.com/karolskolasinski/api_json_recipepuppy\",\n" +
                "    \"description\": null,\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/karolskolasinski/api_json_recipepuppy/deployments\",\n" +
                "    \"created_at\": \"2019-07-22T12:27:59Z\",\n" +
                "    \"updated_at\": \"2020-02-09T20:11:33Z\",\n" +
                "    \"pushed_at\": \"2019-11-18T14:11:44Z\",\n" +
                "    \"git_url\": \"git://github.com/karolskolasinski/api_json_recipepuppy.git\",\n" +
                "    \"ssh_url\": \"git@github.com:karolskolasinski/api_json_recipepuppy.git\",\n" +
                "    \"clone_url\": \"https://github.com/karolskolasinski/api_json_recipepuppy.git\",\n" +
                "    \"svn_url\": \"https://github.com/karolskolasinski/api_json_recipepuppy\",\n" +
                "    \"homepage\": null,\n" +
                "    \"size\": 7,\n" +
                "    \"stargazers_count\": 0,\n" +
                "    \"watchers_count\": 0,\n" +
                "    \"language\": \"Java\",\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_projects\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": false,\n" +
                "    \"forks_count\": 0,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"archived\": false,\n" +
                "    \"disabled\": false,\n" +
                "    \"open_issues_count\": 0,\n" +
                "    \"license\": null,\n" +
                "    \"forks\": 0,\n" +
                "    \"open_issues\": 0,\n" +
                "    \"watchers\": 0,\n" +
                "    \"default_branch\": \"master\"\n" +
                "  }]";


        Type collectionType = new TypeToken<Collection<UserRepos>>() {
        }.getType();
        Collection<UserRepos> fromJson = gson.fromJson(val1, collectionType);

        UserRepos next = fromJson.iterator().next();


    }
}
