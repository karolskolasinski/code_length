package pl.karolskolasinski.code_length.utils;

import pl.karolskolasinski.code_length.model.UserRepos;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserLanguageUtil {

    /**
     * Collecting each language for the list and calculating the most common.
     *
     * @param userRepos: list of UserRepos (given by getUserRepos() from CodeLengthUtil class).
     */
    public String userLanguage(Collection<UserRepos> userRepos) {
        Set<String> languages = userRepos.stream()
                .map(UserRepos::getLanguage)
                .collect(Collectors.toList())
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .keySet();

        return recognizeUserLanguage(languages);
    }

    /**
     * Returns the user's language. If the language list size is 0, then the language is not recognized.
     *
     * @param languages: collection of languages.
     */
    private String recognizeUserLanguage(Set<String> languages) {
        if (languages.size() == 0) {
            return "language not recognized";
        }

        String userLanguage = languages.iterator().next();

        if (userLanguage.equals("HTML") || userLanguage.equals("CSS")) {
            return "Front-end developer";
        }

        return userLanguage;
    }

    /**
     * Returns the full list of user public repositories names (forked included).
     *
     * @param userRepos: list of UserRepos (given by getUserRepos() from CodeLengthUtil class).
     */
    public List<String> reposNames(Collection<UserRepos> userRepos) {
        return userRepos.stream()
                .map(UserRepos::getName)
                .collect(Collectors.toList());
    }
}
