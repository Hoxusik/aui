package lab.aui.entities;

import lombok.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of = {"title", "releaseYear"})
@AllArgsConstructor
public class MovieDTO implements Comparable<MovieDTO> {
    private String id;
    private String title;
    private String releaseYear;
    private String director;

    //@Override
    //public int compareTo(MovieDTO other) {
    //    return title.compareTo(other.title);
    //}

    @Override
    public String toString() {
        return String.format("Title: %s | BirthYear: %d | Director: %s", title, releaseYear, director);
    }
    public static MovieDTO from(Movie movie) {
        return MovieDTO.builder()
                .id(movie.getId().toString())
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear().toString())
                .director(movie.getDirector().getFirstName() + " " + movie.getDirector().getLastName())
                .build();
    }
    @Override
    public int compareTo(MovieDTO other) {
        return Comparator.comparing(MovieDTO::getTitle)
                .thenComparing(MovieDTO::getReleaseYear)
                .compare(this, other);
    }
}
