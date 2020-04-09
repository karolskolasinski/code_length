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
    private final int numberOfPublicRepos;
    private final double length;
    private final String language;
    private final List<String> reposNames;
    private Collection<UserRepos> userRepos;

    private NumberOfReposUtil numberOfReposUtil = new NumberOfReposUtil();
    private CodeLengthUtil codeLengthUtil = new CodeLengthUtil();
    private UserLanguageUtil userLanguageUtil = new UserLanguageUtil();

    public ObjectToDisplay(String username) {
        this.username = username;
        this.numberOfPublicRepos = numberOfReposUtil.getNumberOfPublicRepos(username);
        this.length = codeLengthUtil.codeLengthMeter(username, numberOfPublicRepos);
        this.userRepos = codeLengthUtil.getUserRepos();
        this.language = userLanguageUtil.userLanguage(userRepos);
        this.reposNames = userLanguageUtil.reposNames(userRepos);
    }

}
