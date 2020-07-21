package pl.karolskolasinski.code_length.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karolskolasinski.code_length.model.dto.ObjectToDisplay;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;
import pl.karolskolasinski.code_length.repository.UserCodeLengthRepository;

import java.util.Set;

@Service
public class UserCodeLengthService {

    private final UserCodeLengthRepository userCodeLengthRepository;


    @Autowired
    public UserCodeLengthService(UserCodeLengthRepository userCodeLengthRepository) {
        this.userCodeLengthRepository = userCodeLengthRepository;
    }


    public void saveUserToDatabase(ObjectToDisplay objectToDisplay) {
        UserCodeLength userCodeLength = new UserCodeLength(
                objectToDisplay.getUsername(),
                objectToDisplay.getGithubId(),
                objectToDisplay.getNumberOfPublicRepos(),
                objectToDisplay.getLength(),
                objectToDisplay.getLanguage());

        userCodeLengthRepository.save(userCodeLength);
    }


    public Set<UserCodeLength> getTop10() {
        return userCodeLengthRepository.selectTop10();
    }


    public ObjectToDisplay getUserDetails(String username, String token) {
        return new ObjectToDisplay(username, token);
    }


    public String getUsernameByGithubId(String githubId) {
        return userCodeLengthRepository.findFirstByGithubId(githubId).getUsername();
    }


    // regex for only letters and numbers
    public boolean incorrectUsername(String username) {
        return !username.matches("^[a-zA-Z0-9]+$");
    }

}
