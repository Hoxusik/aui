package lab.aui.entities;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of = {"title", "releaseYear"})
@AllArgsConstructor
public class MovieDTO implements Comparable<MovieDTO> {
    private String title;
    private int releaseYear;
    private String director;

    @Override
    public int compareTo(MovieDTO other) {
        return title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return String.format("Title: %s | BirthYear: %d | Director: %s", title, releaseYear, director);
    }
}
