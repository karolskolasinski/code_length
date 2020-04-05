package pl.karolskolasinski.code_length.api;

public class APIGithubURLBuilder {

    private final StringBuilder builder = new StringBuilder();
    private String raw = "https://raw.githubusercontent.com/karolskolasinski/speech_generator/master/css/style.css";

    public String getUserReposURL(String username, int page) {
        String url = "https://api.github.com/users/{username}/repos?per_page=30&page=";
        return url.replace("{username}", username) + page;
    }

    public String getUserProfileURL(String username) {
        String url = "https://api.github.com/users/{username}";
        return url.replace("{username}", username);
    }

}
