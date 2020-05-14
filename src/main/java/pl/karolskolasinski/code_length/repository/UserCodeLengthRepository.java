package pl.karolskolasinski.code_length.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;

import java.util.Set;

public interface UserCodeLengthRepository extends CrudRepository<UserCodeLength, Integer> {

    @Query(value = "SELECT * FROM (SELECT DISTINCT ON (username) * FROM users ORDER BY username, length DESC) u ORDER BY length DESC LIMIT 10", nativeQuery = true)
    Set<UserCodeLength> selectTop10();

    UserCodeLength findFirstByGithubId(String githubId);
}
