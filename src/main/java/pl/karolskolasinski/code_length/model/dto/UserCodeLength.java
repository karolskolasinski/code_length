package pl.karolskolasinski.code_length.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserCodeLength {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numberOfRepos;

    @NotNull
    private String username;

    private double length;

    private String language;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    public UserCodeLength(int numberOfRepos, @NotNull String username, double length, String language) {
        this.numberOfRepos = numberOfRepos;
        this.username = username;
        this.length = length;
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCodeLength)) return false;
        UserCodeLength that = (UserCodeLength) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
