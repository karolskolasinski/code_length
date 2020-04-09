package pl.karolskolasinski.code_length.utils;

import pl.karolskolasinski.code_length.model.UserRepos;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserLanguageUtil {

    /**
     * @param userRepos
     */
    public String userLanguage(Collection<UserRepos> userRepos) {
        Set<String> strings = userRepos.stream()
                .map(UserRepos::getLanguage)
                .collect(Collectors.toList())
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .keySet();

        return recognizeUserLanguage(strings);
    }

    /**
     * @param strings
     */
    private String recognizeUserLanguage(Set<String> strings) {
        if (strings.size() == 0) {
            return "language not recognized";
        }

        String userProfession = strings.iterator().next();

        if (userProfession.equals("HTML") || userProfession.equals("CSS")) {
            return "Front-end developer";
        }

        return userProfession;
    }

    /**
     * @param userRepos
     */
    public List<String> reposNames(Collection<UserRepos> userRepos) {
        return userRepos.stream()
                .map(UserRepos::getName)
                .collect(Collectors.toList());
    }
}
