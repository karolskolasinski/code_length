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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class GithubService {

    private APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();

    private int numberOfRepos;

    private Collection<UserRepos> userRepos = new ArrayList<>();

    public int numberOfRepos(String username) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        StringBuilder stringBuilder = new StringBuilder();


        String userProfile = apiGithubURLBuilder.getUserProfile(username);

        try {
            URL url = new URL(userProfile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            reader.lines().forEach(stringBuilder::append);
            User user = gson.fromJson(stringBuilder.toString(), User.class);
            double public_repos = user.getPublic_repos();
            numberOfRepos = (int) public_repos;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numberOfRepos;
    }

    public double codeLengthMeter(String username) {
        String userProfile = apiGithubURLBuilder.getUserProfileURL(username, numberOfRepos);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(userProfile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            reader.lines().forEach(stringBuilder::append);
            Type collectionType = new TypeToken<Collection<UserRepos>>() {
            }.getType();
            Collection<UserRepos> fromJson = gson.fromJson(stringBuilder.toString(), collectionType);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

//        System.out.println(userRepos.iterator().next().getArchive_url());
        return 0;
    }

    public String language(String username) {


        return null;
    }
}
