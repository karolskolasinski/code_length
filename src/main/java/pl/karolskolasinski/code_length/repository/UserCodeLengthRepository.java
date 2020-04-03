package pl.karolskolasinski.code_length.repository;

import org.springframework.data.repository.CrudRepository;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;

public interface UserCodeLengthRepository extends CrudRepository<UserCodeLength, Integer> {

}
