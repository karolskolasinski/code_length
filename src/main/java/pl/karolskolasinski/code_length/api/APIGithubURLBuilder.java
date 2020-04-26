package pl.karolskolasinski.code_length.api;

public class APIGithubURLBuilder {

    public String getUserProfileURL(String username) {
        String url = "https://api.github.com/users/{username}?client_id=be5b18acc8cf6961ff2c&client_secret=b8b44827071e2a35581beb69c64843fbf4433e76";
        return url.replace("{username}", username);
    }

    public String getUserReposURL(String username, int page) {
        String url = "https://api.github.com/users/{username}/repos?client_id=be5b18acc8cf6961ff2c&client_secret=b8b44827071e2a35581beb69c64843fbf4433e76&per_page=30&page=";
        return url.replace("{username}", username) + page;
    }

    public String getSingleRepositoryURL(String username, String repositoryName) {
        String url = "https://api.github.com/repos/{username}/{repositoryName}/git/trees/master?recursive=1&client_id=be5b18acc8cf6961ff2c&client_secret=b8b44827071e2a35581beb69c64843fbf4433e76";
        return url.replace("{username}", username).replace("{repositoryName}", repositoryName);
    }

}
