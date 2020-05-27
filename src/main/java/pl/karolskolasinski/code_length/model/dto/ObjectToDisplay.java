package pl.karolskolasinski.code_length.model.dto;

import lombok.Getter;
import pl.karolskolasinski.code_length.model.UserRepos;
import pl.karolskolasinski.code_length.utils.CodeLengthUtil;
import pl.karolskolasinski.code_length.utils.NumberOfReposUtil;
import pl.karolskolasinski.code_length.utils.UserLanguageUtil;

import java.util.Collection;
import java.util.List;

@Getter
public class ObjectToDisplay {

    private final String username;
    private String githubId;
    private final int numberOfPublicRepos;
    private final double length;
    private final String language;
    private final List<String> reposNames;
    private final Collection<UserRepos> userRepos;

    private final NumberOfReposUtil numberOfReposUtil = new NumberOfReposUtil();
    private final CodeLengthUtil codeLengthUtil = new CodeLengthUtil();
    private final UserLanguageUtil userLanguageUtil = new UserLanguageUtil();

    public ObjectToDisplay(String username, String token) {
        this.username = username;
        this.githubId = numberOfReposUtil.getGithubId(username, token);
        this.numberOfPublicRepos = numberOfReposUtil.getNumberOfPublicRepos(username, token);
        this.length = codeLengthUtil.codeLengthMeter(username, numberOfPublicRepos, token);
        this.userRepos = codeLengthUtil.getUserRepos();
        this.language = userLanguageUtil.userLanguage(userRepos);
        this.reposNames = userLanguageUtil.reposNames(userRepos);
    }

    public ObjectToDisplay(String username, int numberOfPublicRepos, double length, String language, List<String> reposNames, Collection<UserRepos> userRepos) {
        this.username = username;
        this.numberOfPublicRepos = numberOfPublicRepos;
        this.length = length;
        this.language = language;
        this.reposNames = reposNames;
        this.userRepos = userRepos;
    }
}
