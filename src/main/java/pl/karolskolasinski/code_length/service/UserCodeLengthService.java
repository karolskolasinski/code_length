package pl.karolskolasinski.code_length.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karolskolasinski.code_length.model.dto.ObjectToDisplay;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;
import pl.karolskolasinski.code_length.repository.UserCodeLengthRepository;

import java.util.ArrayList;
import java.util.List;

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
                objectToDisplay.getNumberOfPublicRepos(),
                objectToDisplay.getLength(),
                objectToDisplay.getLanguage());

        userCodeLengthRepository.save(userCodeLength);
    }

    public List<UserCodeLength> top10() {
        return new ArrayList<>(userCodeLengthRepository.findFirst10ByOrderByLengthDesc());
    }

    public ObjectToDisplay getUserDetails(String username) {
        return new ObjectToDisplay(username);
    }

    /**
     * Regex for only letters and numbers
     */
    public boolean incorrectUsername(String username) {
        return !username.matches("^[a-zA-Z0-9]+$");
    }
}
