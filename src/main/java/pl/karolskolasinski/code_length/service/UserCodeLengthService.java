package pl.karolskolasinski.code_length.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void saveUserToDatabase( String username, int numberOfPublicRepos, double length, String language) {
        UserCodeLength userCodeLength = new UserCodeLength(username, numberOfPublicRepos, length, language);
        userCodeLengthRepository.save(userCodeLength);
    }

    public List<UserCodeLength> top10() {
        return new ArrayList<>(userCodeLengthRepository.findFirst10ByOrderByLengthDesc());
    }

}
