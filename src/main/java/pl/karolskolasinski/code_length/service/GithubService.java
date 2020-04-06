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
    private int publicRepos = -1;
    private double kilometersFromRepos = -1;
    private Collection<UserRepos> userRepos = new ArrayList<>();
    private double km;
    private List<Integer> eachFileLength = new ArrayList<>();
    private List<String> supportedFiles = new ArrayList<>();

    public GithubService() {
        supportedFiles.add(".java");
        supportedFiles.add(".html");
        supportedFiles.add(".css");
        supportedFiles.add(".js");
    }

    /**
     *
     */
    public int numberOfRepos(String username) {
        this.username = username;
        StringBuilder sb = new StringBuilder();
        String userProfileURL = apiGithubURLBuilder.getUserProfileURL(username);

        try {
            readJSONFromURLByStringBuilder(sb, userProfileURL);
            User user = gson.fromJson(sb.toString(), User.class);
            publicRepos = user.getPublicRepos();
        } catch (IOException e) {
            publicRepos = -1;
            e.printStackTrace();
        }

        return publicRepos;
    }

    /**
     *
     */
    public double codeLengthMeter() {
        int numberOfPages = getNumberOfPages();

        for (int i = 1; i <= numberOfPages; i++) {
            String userProfile = apiGithubURLBuilder.getUserReposURL(username, i);
            StringBuilder stringBuilder = new StringBuilder();

            kilometersFromRepos = getKilometersFromRepos(userProfile, stringBuilder);
        }

        return kilometersFromRepos;
    }

    private double getKilometersFromRepos(String userProfile, StringBuilder stringBuilder) {
        try {
            readJSONFromURLByStringBuilder(stringBuilder, userProfile);

            Type collectionType = new TypeToken<Collection<UserRepos>>() {
            }.getType();
            userRepos.addAll(gson.fromJson(stringBuilder.toString(), collectionType));

            kilometersFromRepos = getKilometersFromRepos();
        } catch (IOException e) {
            kilometersFromRepos = -1;
            e.printStackTrace();
        }

        return kilometersFromRepos;
    }

    /**
     *
     */
    private double getKilometersFromRepos() {
        userRepos.stream().map(UserRepos::getName).forEach(repoName -> {
            String singleRepositoryURL = apiGithubURLBuilder.getSingleRepositoryURL(username, repoName);
            addSingleRepoToList(singleRepositoryURL);
        });

        return countKilometers();
    }

    /**
     *
     */
    private double countKilometers() {
        eachFileLength.forEach(length -> km += length * 0.0000002645833);
        return roundOff();
    }

    /**
     *
     */
    private double roundOff() {
        return Math.round(km * 100.0) / 100.0;
    }

    /**
     *
     */
    private void addSingleRepoToList(String singleRepositoryURL) {
        try {
            StringBuilder sb = new StringBuilder();

            readJSONFromURLByStringBuilder(sb, singleRepositoryURL);
            SingleRepo singleRepo = gson.fromJson(sb.toString(), SingleRepo.class);

            singleRepo.getTree().forEach(this::searchForSupportedFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void searchForSupportedFiles(Tree singleRepoTree) {
        String path = singleRepoTree.getPath();
        supportedFiles.forEach(supportedFile -> checkIsSupported(singleRepoTree, path, supportedFile));
    }

    /**
     *
     */
    private void checkIsSupported(Tree singleRepoTree, String path, String supportedFile) {
        if (path.contains(supportedFile)) {
            eachFileLength.add(singleRepoTree.getSize());
        }
    }

    /**
     *
     */
    private void readJSONFromURLByStringBuilder(StringBuilder sb, String URLAddress) throws IOException {
        URL url = new URL(URLAddress);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        reader.lines().forEach(sb::append);
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
