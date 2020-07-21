package pl.karolskolasinski.code_length.model.dto;

import lombok.AllArgsConstructor;
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


    @NotNull
    private String username;


    @NotNull
    private String githubId;


    private int numberOfRepos;
    private double length;
    private String language;


    @CreationTimestamp
    private LocalDateTime localDateTime;


    public UserCodeLength(@NotNull String username, @NotNull String githubId, int numberOfRepos, double length, String language) {
        this.username = username;
        this.githubId = githubId;
        this.numberOfRepos = numberOfRepos;
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
