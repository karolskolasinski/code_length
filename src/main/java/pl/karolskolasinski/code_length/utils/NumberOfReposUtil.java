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

    private APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();
    private JSONReader jsonReader = new JSONReader();
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();

    /**
     * Returns number of user public repos. Reads JSON from user profile URL, parses to User object.
     *
     * @param username: provided username in input form.
     */
    public int getNumberOfPublicRepos(String username) {
        int numberOfPublicRepos;

        try {
            StringBuilder sb = new StringBuilder();
            String userProfileURL = apiGithubURLBuilder.getUserProfileURL(username);

            jsonReader.readJSONFromURLByStringBuilder(sb, userProfileURL);
            User user = gson.fromJson(sb.toString(), User.class);

            numberOfPublicRepos = user.getPublicRepos();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                numberOfPublicRepos = -2;
                System.err.println("User not found.");
                e.printStackTrace();
            } else {
                numberOfPublicRepos = -1;
                e.printStackTrace();
            }
        }

        return numberOfPublicRepos;
    }

}
