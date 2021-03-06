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
    private final APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();
    private final JSONReader jsonReader = new JSONReader();
    private final GsonBuilder gsonBuilder = new GsonBuilder();
    private final Gson gson = gsonBuilder.create();
    private double kilometersFromRepos = -1;
    private double km;
    private final List<Integer> eachFileSize = new ArrayList<>();
    private final List<String> supportedFiles;
    private final Collection<UserRepos> userRepos = new ArrayList<>();
    private static final double CHAR_LENGTH_IN_PIXELS = 7;
    private static final double PIXEL_IN_KILOMETER = 0.0000002645833;
    private static final double MULTIPLIER = CHAR_LENGTH_IN_PIXELS * PIXEL_IN_KILOMETER;


    /**
     * Initializing and filling the list of supported files with the extensions the program is looking for.
     */
    public CodeLengthUtil() {
        supportedFiles = new ArrayList<>();
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
     * Returns kilometers of user code. Reads JSON from every page of user repos, parses to collection of UserRepos (loop by number of pages).
     *
     * @param username            :            provided username in input form.
     * @param numberOfPublicRepos : number of user public repos (given by getNumberOfPublicRepos(String username) from NumberOfReposUtil class).
     * @param token
     */
    public double codeLengthMeter(String username, int numberOfPublicRepos, String token) {
        this.username = username;
        this.numberOfPublicRepos = numberOfPublicRepos;
        int numberOfPages = getNumberOfPages();

        for (int i = 1; i <= numberOfPages; i++) {
            try {
                String userProfile = apiGithubURLBuilder.getUserReposURL(username, i);
                StringBuilder sb = new StringBuilder();
                jsonReader.readJSONFromURLByStringBuilder(sb, userProfile, token);

                Type collectionType = new TypeToken<Collection<UserRepos>>() {
                }.getType();
                userRepos.addAll(gson.fromJson(sb.toString(), collectionType));

                kilometersFromRepos = getKilometersFromRepos(token);
            } catch (IOException e) {
                kilometersFromRepos = -1;
                System.err.println(e.getMessage());
            }
        }

        return kilometersFromRepos;
    }


    /**
     * Calculates the number of pages from the number of public user repositories.
     */
    private int getNumberOfPages() {
        return (int) Math.ceil(numberOfPublicRepos / 30.0);
    }


    /**
     * Returns kilometers of user code. Get URL for each non forked repository.
     *
     * @param token
     */
    private double getKilometersFromRepos(String token) {
        userRepos.stream()
                .filter(userRepo -> !userRepo.isFork())
                .map(UserRepos::getName)
                .map(repoName -> apiGithubURLBuilder.getSingleRepositoryURL(username, repoName))
                .forEach(singleRepositoryURL -> addSingleRepoToList(singleRepositoryURL, token))
        ;

        return countKilometers();
    }


    /**
     * Calculates kilometers of user code by multiplying each file size by MULTIPLIER.
     */
    private double countKilometers() {
        eachFileSize.forEach(length -> km += length * MULTIPLIER);
        return roundOff();
    }


    /**
     * Rounds the product of the kilometer of the code.
     */
    private double roundOff() {
        return Math.round(km * 100.0) / 100.0;
    }


    /**
     * Reads JSON from every single user repo, parses to SingleRepo object.
     *
     * @param singleRepositoryURL: URL of single repo built based on each repoName from the userRepos list.
     */
    private void addSingleRepoToList(String singleRepositoryURL, String token) {
        try {
            StringBuilder sb = new StringBuilder();

            jsonReader.readJSONFromURLByStringBuilder(sb, singleRepositoryURL, token);
            SingleRepo singleRepo = gson.fromJson(sb.toString(), SingleRepo.class);

            singleRepo.getTree().forEach(this::searchForSupportedFiles);
        } catch (IOException e) {
            kilometersFromRepos = -1;
        }
    }


    /**
     * Adds the file size to the list if the file extension is in the list of supported files.
     *
     * @param singleRepoTree: Tree object from SingleRepo.
     */
    private void searchForSupportedFiles(Tree singleRepoTree) {
        String path = singleRepoTree.getPath();
        supportedFiles.stream()
                .filter(path::contains)
                .forEach(supportedFile -> eachFileSize.add(singleRepoTree.getSize()));
    }


    public Collection<UserRepos> getUserRepos() {
        return userRepos;
    }

}
