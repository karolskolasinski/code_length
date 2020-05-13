package pl.karolskolasinski.code_length.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;

import javax.persistence.NamedNativeQuery;
import java.lang.annotation.Native;
import java.util.Set;

public interface UserCodeLengthRepository extends CrudRepository<UserCodeLength, Integer> {

    Set<UserCodeLength> findFirst10ByOrderByLengthDesc();

//    @Query(value = "SELECT username FROM users WHERE id = :id", nativeQuery = true)
    UserCodeLength findFirstByGithubId(String githubId);
}
