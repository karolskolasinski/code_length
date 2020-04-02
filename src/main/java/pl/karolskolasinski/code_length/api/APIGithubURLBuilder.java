package pl.karolskolasinski.code_length.api;

public class APIGithubURLBuilder {

    private final StringBuilder builder = new StringBuilder();
    private String raw = "https://raw.githubusercontent.com/karolskolasinski/speech_generator/master/css/style.css";

    public String getUserProfileURL(String username) {
        String userURL = "https://api.github.com/users/{username}/repos";
        return userURL.replace("{username}", username);
    }

}
