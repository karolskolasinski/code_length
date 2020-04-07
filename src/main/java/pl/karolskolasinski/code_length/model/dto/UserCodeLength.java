package pl.karolskolasinski.code_length.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class UserCodeLength {

    @Id
    private Long id;

    private String username;
    private double length;
    private LocalDateTime localDateTime;

}
