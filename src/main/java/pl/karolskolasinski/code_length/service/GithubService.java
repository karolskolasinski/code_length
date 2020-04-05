package pl.karolskolasinski.code_length.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import pl.karolskolasinski.code_length.api.APIGithubURLBuilder;
import pl.karolskolasinski.code_length.model.SingleRepo;
import pl.karolskolasinski.code_length.model.Tree;
import pl.karolskolasinski.code_length.model.User;
import pl.karolskolasinski.code_length.model.UserRepos;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GithubService {

    private String username;
    private APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private int publicRepos;
    private Collection<UserRepos> userRepos = new ArrayList<>();
    private List<String> repoNames = new ArrayList<>();
    private Collection<SingleRepo> singleRepos = new ArrayList<>();
    private double km;
    private List<Long> eachFileLength = new ArrayList<>();


    /**
     *
     */
    public int numberOfRepos(String username) {
        this.username = username;
        StringBuilder sb = new StringBuilder();
        String userProfileURL = apiGithubURLBuilder.getUserProfileURL(this.username);

        readJSONFromURLByStringBuilder(sb, userProfileURL);
        User user = gson.fromJson(sb.toString(), User.class);

        return publicRepos = user.getPublicRepos();
    }

    /**
     *
     */
    public double codeLengthMeter() {
        int numberOfPages = getNumberOfPages();

        for (int i = 1; i <= numberOfPages; i++) {
            String userProfile = apiGithubURLBuilder.getUserReposURL(username, i);
            StringBuilder stringBuilder = new StringBuilder();

            readJSONFromURLByStringBuilder(stringBuilder, userProfile);

            Type collectionType = new TypeToken<Collection<UserRepos>>() {
            }.getType();
            userRepos.addAll(gson.fromJson(stringBuilder.toString(), collectionType));
        }

        return getKilometersFromRepos();
    }

    /**
     *
     */
    private double getKilometersFromRepos() {
        userRepos.stream().map(UserRepos::getName).forEach(repoName -> {
            StringBuilder sb = new StringBuilder();
            String singleRepositoryURL = apiGithubURLBuilder.getSingleRepositoryURL(username, repoName);
            addSingleRepoToList(sb, singleRepositoryURL, repoName);
        });

        eachFileLength.forEach(length -> {
            km = +length * 0.185206;
        });

        return km;
    }

    /**
     *
     */
    private void addSingleRepoToList(StringBuilder sb, String singleRepositoryURL, String repoName) {
        readJSONFromURLByStringBuilder(sb, singleRepositoryURL);
        SingleRepo singleRepo = gson.fromJson(sb.toString(), SingleRepo.class);
        List<Tree> tree = singleRepo.getTree();

        tree.forEach(singleRepoTree -> {
            if (singleRepoTree.getPath().contains(".html") ||
                    singleRepoTree.getPath().contains(".css") ||
                    singleRepoTree.getPath().contains(".js") ||
                    singleRepoTree.getPath().contains(".java")) {
                getCodeFromSingleFile(repoName, singleRepoTree.getPath());
            }
        });
    }

    /**
     *
     */
    private void getCodeFromSingleFile(String repoName, String path) {
        String rawFileURL = apiGithubURLBuilder.getRawFileURL(username, repoName, path);
        StringBuilder stringBuilder = new StringBuilder();

        readJSONFromURLByStringBuilder(stringBuilder, rawFileURL);

        Long length = (long) stringBuilder.toString().trim().length();
        eachFileLength.add(length);
    }

    /**
     *
     */
    private void readJSONFromURLByStringBuilder(StringBuilder sb, String URLAddress) {
        try {
            URL url = new URL(URLAddress);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            reader.lines().forEach(sb::append);
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
