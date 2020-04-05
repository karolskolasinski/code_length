package pl.karolskolasinski.code_length.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import pl.karolskolasinski.code_length.api.APIGithubURLBuilder;
import pl.karolskolasinski.code_length.model.User;
import pl.karolskolasinski.code_length.model.UserRepos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class GithubService {

    private APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private int publicRepos;
    private Collection<UserRepos> userRepos = new ArrayList<>();

    /**
     *
     */
    public int numberOfRepos(String username) {
        StringBuilder sb = new StringBuilder();
        String userProfileURL = apiGithubURLBuilder.getUserProfileURL(username);

        readJSONFromURLByStringBuilder(sb, userProfileURL);
        User user = gson.fromJson(sb.toString(), User.class);

        return publicRepos = user.getPublicRepos();
    }

    /**
     *
     */
    public double codeLengthMeter(String username) {
        int numberOfPages = getNumberOfPages();

        for (int i = 1; i <= numberOfPages; i++) {
            String userProfile = apiGithubURLBuilder.getUserReposURL(username, i);
            StringBuilder stringBuilder = new StringBuilder();

            readJSONFromURLByStringBuilder(stringBuilder, userProfile);

            Type collectionType = new TypeToken<Collection<UserRepos>>() {
            }.getType();
            userRepos.addAll(gson.fromJson(stringBuilder.toString(), collectionType));
        }


        return 0;
    }

    /**
     *
     */
    private void readJSONFromURLByStringBuilder(StringBuilder stringBuilder, String URLAddress) {
        try {
            URL url = new URL(URLAddress);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            reader.lines().forEach(stringBuilder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private int getNumberOfPages() {
        double v = (double) publicRepos / 30;

        if (v > Math.round(v)) {
            v++;
        }

        return (int) v;
    }

    /**
     *
     */
    public String language(String username) {

        return null;
    }

}
