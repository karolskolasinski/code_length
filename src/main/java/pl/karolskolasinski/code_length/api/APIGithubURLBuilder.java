package pl.karolskolasinski.code_length.api;

public class APIGithubURLBuilder {

    public String getUserProfileURL(String username) {
        String url = "https://api.github.com/users/{username}?client_id=client_id&client_secret=client_secret";
        return url.replace("{username}", username);
    }

    public String getUserReposURL(String username, int page) {
        String url = "https://api.github.com/users/{username}/repos?client_id=client_id&client_secret=client_secret&per_page=30&page=";
        return url.replace("{username}", username) + page;
    }

    public String getSingleRepositoryURL(String username, String repositoryName) {
        String url = "https://api.github.com/repos/{username}/{repositoryName}/git/trees/master?recursive=1&client_id=client_id&client_secret=client_secret";
        return url.replace("{username}", username).replace("{repositoryName}", repositoryName);
    }

}
