package pl.karolskolasinski.code_length.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import pl.karolskolasinski.code_length.api.APIGithubURLBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws IOException {

        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();
        String userProfileURL = apiGithubURLBuilder.getUserProfileURL("karolskolasinski");

        URL url = new URL(userProfileURL);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder stringBuilder = new StringBuilder();

        bufferedReader.lines().forEach(stringBuilder::append);


        Type collectionType = new TypeToken<Collection<UserRepos>>() {
        }.getType();
        Collection<UserRepos> fromJson = gson.fromJson(stringBuilder.toString(), collectionType);

        System.err.println(fromJson.size());

//        UserRepos next = fromJson.iterator().next();
//        System.out.println(next.getOwner().getLogin());


    }
}
