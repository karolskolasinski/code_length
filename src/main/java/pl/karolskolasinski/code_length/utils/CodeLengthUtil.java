package pl.karolskolasinski.code_length.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import pl.karolskolasinski.code_length.api.APIGithubURLBuilder;
import pl.karolskolasinski.code_length.model.SingleRepo;
import pl.karolskolasinski.code_length.model.Tree;
import pl.karolskolasinski.code_length.model.UserRepos;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CodeLengthUtil {

    private String username;
    private int numberOfPublicRepos;
    private APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();
    private JSONReader jsonReader = new JSONReader();
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private double kilometersFromRepos = -1;
    private double km;
    private List<Integer> eachFileLength = new ArrayList<>();
    private List<String> supportedFiles = new ArrayList<>();
    private Collection<UserRepos> userRepos = new ArrayList<>();
    private static final double CHAR_LENGTH_IN_PIXELS = 7;
    private static final double PIXEL_IN_KILOMETER = 0.0000002645833;
    private static final double MULTIPLIER = CHAR_LENGTH_IN_PIXELS * PIXEL_IN_KILOMETER;

    /**
     *
     */
    public CodeLengthUtil() {
        supportedFiles.add(".java");
        supportedFiles.add(".kt");
        supportedFiles.add(".html");
        supportedFiles.add(".css");
        supportedFiles.add(".js");
        supportedFiles.add(".php");
        supportedFiles.add(".c");
        supportedFiles.add(".cs");
        supportedFiles.add(".cpp");
        supportedFiles.add(".py");
        supportedFiles.add(".rb");
        supportedFiles.add(".ex");
        supportedFiles.add(".go");
    }

    /**
     *
     */
    public double codeLengthMeter(String username, int numberOfPublicRepos) {
        this.username = username;
        this.numberOfPublicRepos = numberOfPublicRepos;
        int numberOfPages = getNumberOfPages();

        for (int i = 1; i <= numberOfPages; i++) {
            try {
                String userProfile = apiGithubURLBuilder.getUserReposURL(username, i);
                StringBuilder sb = new StringBuilder();
                jsonReader.readJSONFromURLByStringBuilder(sb, userProfile);

                Type collectionType = new TypeToken<Collection<UserRepos>>() {
                }.getType();
                userRepos.addAll(gson.fromJson(sb.toString(), collectionType));

                kilometersFromRepos = getKilometersFromRepos();
            } catch (IOException e) {
                kilometersFromRepos = -1;
                e.printStackTrace();
            }
        }

        return kilometersFromRepos;
    }

    /**
     *
     */
    private int getNumberOfPages() {
        double pages = (double) numberOfPublicRepos / 30;
        if (pages > Math.round(pages)) {
            pages++;
        }
        return (int) pages;
    }

    /**
     *
     */
    private double getKilometersFromRepos() {
        userRepos.stream()
                .filter(userRepo -> !userRepo.isFork())
                .map(UserRepos::getName)
                .map(repoName -> apiGithubURLBuilder.getSingleRepositoryURL(username, repoName))
                .forEach(this::addSingleRepoToList);

        return countKilometers();
    }

    /**
     *
     */
    private double countKilometers() {
        eachFileLength.forEach(length -> km += length * MULTIPLIER);
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

            jsonReader.readJSONFromURLByStringBuilder(sb, singleRepositoryURL);
            SingleRepo singleRepo = gson.fromJson(sb.toString(), SingleRepo.class);

            singleRepo.getTree().forEach(this::searchForSupportedFiles);
        } catch (IOException e) {
            kilometersFromRepos = -1;
        }
    }

    /**
     *
     */
    private void searchForSupportedFiles(Tree singleRepoTree) {
        String path = singleRepoTree.getPath();
        supportedFiles.stream()
                .filter(path::contains)
                .forEach(supportedFile -> eachFileLength.add(singleRepoTree.getSize()));
    }

    public Collection<UserRepos> getUserRepos() {
        return userRepos;
    }
}
