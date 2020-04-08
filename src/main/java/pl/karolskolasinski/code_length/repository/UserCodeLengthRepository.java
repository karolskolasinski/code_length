package pl.karolskolasinski.code_length.repository;

import org.springframework.data.repository.CrudRepository;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;

import java.util.Set;

public interface UserCodeLengthRepository extends CrudRepository<UserCodeLength, Integer> {

    Set<UserCodeLength> findFirst10ByOrderByLengthDesc();
}
