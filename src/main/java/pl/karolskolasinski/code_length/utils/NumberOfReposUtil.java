package pl.karolskolasinski.code_length.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import pl.karolskolasinski.code_length.api.APIGithubURLBuilder;
import pl.karolskolasinski.code_length.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class NumberOfReposUtil {

    private static final String USER_NOT_FOUND = "User not found.";
    private final APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();
    private final JSONReader jsonReader = new JSONReader();
    private final GsonBuilder gsonBuilder = new GsonBuilder();
    private final Gson gson = gsonBuilder.create();


    /**
     * Returns number of user public repos. Reads JSON from user profile URL, parses to User object.
     *
     * @param username: provided username in input form.
     */
    public int getNumberOfPublicRepos(String username, String token) {
        int numberOfPublicRepos;

        try {
            User user = getUser(username, token);

            numberOfPublicRepos = user.getPublicRepos();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                numberOfPublicRepos = -2;
                System.err.println(USER_NOT_FOUND);
                System.err.println(e.getMessage());
            } else {
                numberOfPublicRepos = -1;
                System.err.println(e.getMessage());
            }
        }

        return numberOfPublicRepos;
    }


    public String getGithubId(String username, String token) {
        String id = USER_NOT_FOUND;

        try {
            id = getUser(username, token).getId();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return id;
    }


    private User getUser(String username, String token) throws IOException {
        StringBuilder sb = new StringBuilder();
        String userProfileURL = apiGithubURLBuilder.getUserProfileURL(username);

        jsonReader.readJSONFromURLByStringBuilder(sb, userProfileURL, token);
        return gson.fromJson(sb.toString(), User.class);
    }

}
