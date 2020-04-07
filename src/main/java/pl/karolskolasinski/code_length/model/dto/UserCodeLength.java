package pl.karolskolasinski.code_length.model.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class UserCodeLength {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;

    private double length;

    private String language;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    public UserCodeLength(String username, double length, String language) {
        this.username = username;
        this.length = length;
        this.language = language;
    }
}
