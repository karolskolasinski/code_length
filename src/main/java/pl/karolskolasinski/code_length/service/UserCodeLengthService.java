package pl.karolskolasinski.code_length.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;
import pl.karolskolasinski.code_length.repository.UserCodeLengthRepository;

@Service
public class UserCodeLengthService {

    private final UserCodeLengthRepository userCodeLengthRepository;

    @Autowired
    public UserCodeLengthService(UserCodeLengthRepository userCodeLengthRepository) {
        this.userCodeLengthRepository = userCodeLengthRepository;
    }

    public void saveUserToDatabase(UserCodeLength userCodeLength) {
        userCodeLengthRepository.save(userCodeLength);
    }


}
