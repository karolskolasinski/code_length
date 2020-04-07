package pl.karolskolasinski.code_length.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import pl.karolskolasinski.code_length.api.APIGithubURLBuilder;
import pl.karolskolasinski.code_length.model.SingleRepo;
import pl.karolskolasinski.code_length.model.Tree;
import pl.karolskolasinski.code_length.model.User;
import pl.karolskolasinski.code_length.model.UserRepos;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GithubUtil {

    private String username;
    private APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private int publicRepos = -1;
    private double kilometersFromRepos = -1;
    private String language;
    private Collection<UserRepos> userRepos = new ArrayList<>();
    private double km;
    private List<Integer> eachFileLength = new ArrayList<>();
    private List<String> supportedFiles = new ArrayList<>();
    private static final double CHAR_LENGTH_IN_PIXEL = 7;
    private static final double PIXEL_IN_KILOMETER = 0.0000002645833;
    private static final double MULTIPLIER = CHAR_LENGTH_IN_PIXEL * PIXEL_IN_KILOMETER;

    public GithubUtil() {
        supportedFiles.add(".java");
        supportedFiles.add(".html");
        supportedFiles.add(".css");
        supportedFiles.add(".js");
    }

    /**
     *
     */
    public int numberOfRepos(String username) {
        try {
            this.username = username;
            StringBuilder sb = new StringBuilder();
            String userProfileURL = apiGithubURLBuilder.getUserProfileURL(username);
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
            try {
                String userProfile = apiGithubURLBuilder.getUserReposURL(username, i);
                StringBuilder sb = new StringBuilder();
                readJSONFromURLByStringBuilder(sb, userProfile);

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

            readJSONFromURLByStringBuilder(sb, singleRepositoryURL);
            SingleRepo singleRepo = gson.fromJson(sb.toString(), SingleRepo.class);

            singleRepo.getTree().forEach(this::searchForSupportedFiles);
        } catch (IOException e) {
            kilometersFromRepos = -1;
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
        double pages = (double) publicRepos / 30;
        if (pages > Math.round(pages)) {
            pages++;
        }
        return (int) pages;
    }

    /**
     *
     */
    public UserCodeLength createUser() {
        return new UserCodeLength(publicRepos, username, roundOff(), language);
    }

    /**
     *
     */
    public String language(String username) {

        return null;
    }

}
