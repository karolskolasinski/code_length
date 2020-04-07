package pl.karolskolasinski.code_length.repository;

import org.springframework.data.repository.CrudRepository;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;

import java.util.List;

public interface UserCodeLengthRepository extends CrudRepository<UserCodeLength, Integer> {

//    List<UserCodeLength> findTop10By();
    List<UserCodeLength> findFirst10ByOrderByLengthDesc();
}
