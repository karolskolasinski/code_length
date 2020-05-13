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

    private String username;
    private String githubId;
    private int numberOfPublicRepos;
    private double length;
    private String language;
    private List<String> reposNames;
    private Collection<UserRepos> userRepos;

    private NumberOfReposUtil numberOfReposUtil = new NumberOfReposUtil();
    private CodeLengthUtil codeLengthUtil = new CodeLengthUtil();
    private UserLanguageUtil userLanguageUtil = new UserLanguageUtil();

    public ObjectToDisplay(String username) {
        this.username = username;
        this.githubId = numberOfReposUtil.getGithubId(username);
        this.numberOfPublicRepos = numberOfReposUtil.getNumberOfPublicRepos(username);
        this.length = codeLengthUtil.codeLengthMeter(username, numberOfPublicRepos);
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
