package lab.aui.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of = {"title", "releaseYear"})
@AllArgsConstructor
public class MovieDTO implements Comparable<MovieDTO> {
    private UUID id;
    private String title;
    private int releaseYear;
    private String director;
    private UUID directorId;
    @Override
    public int compareTo(MovieDTO other) {
        return title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return String.format("Title: %s | BirthYear: %d | Director: %s", title, releaseYear, director);
    }
}
