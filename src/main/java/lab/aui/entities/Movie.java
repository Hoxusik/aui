package lab.aui.entities;
import lombok.*;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of = {"title", "releaseYear"})
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie implements Comparable<Movie>, Serializable {
    @Id
    private UUID id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "release_year", nullable = false)
    private int releaseYear;
    @ManyToOne//(optional = false)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @Override
    public int compareTo(Movie other) {
        return title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return String.format("Title: %s | BirthYear: %d ", title, releaseYear);
    }

}
