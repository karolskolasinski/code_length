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
import java.net.URLConnection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        APIGithubURLBuilder apiGithubURLBuilder = new APIGithubURLBuilder();
        String userProfileURL = apiGithubURLBuilder.getUserProfileURL("karolskolasinski", 1);

        URL url = new URL(userProfileURL);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder stringBuilder = new StringBuilder();

        bufferedReader.lines().forEach(stringBuilder::append);



        Type collectionType = new TypeToken<Collection<UserRepos>>() {
        }.getType();
        Collection<UserRepos> fromJson = gson.fromJson(stringBuilder.toString(), collectionType);

        System.out.println(fromJson.iterator().next().getBranches_url());
//        User user = fromJson.iterator().next();
//        System.out.println(user.getPublic_repos());

        URLConnection conn = url.openConnection();

//        //get all headers
//        Map<String, List<String>> map = conn.getHeaderFields();
//        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//            System.out.println("Key : " + entry.getKey() +
//                    " ,Value : " + entry.getValue());
//        }
//
//        //get header by 'key'
//        String server = conn.getHeaderField("Server");




//        UserRepos next = fromJson.iterator().next();
//        System.out.println(next.getOwner().getLogin());


    }
}
