package lab.aui.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "directors")
public class Director implements Comparable<Director>, Serializable {
    @Id
    private UUID id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;
    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default List<Movie> movies = new ArrayList<>();


    @Override
    public int compareTo(Director other) {
        int cmp = lastName.compareTo(other.lastName);
        if (cmp == 0) {
            return firstName.compareTo(other.firstName);
        }
        return cmp;
    }


    // Metoda pomocnicza do dwukierunkowego ustawiania relacji
    public void addMovie(Movie movie) {
        if(movie!=null) {
            movies.add(movie);
            movie.setDirector(this);
        }
    }

    public void removeMovie(Movie movie) {
        if(movie!=null) {
            movies.remove(movie);
            movie.setDirector(null);
        }
    }
    @Override
    public String toString() {
        return String.format("Name: %s | Surname: %s | BirthYear: %d ", firstName, lastName, birthYear);
    }
}
