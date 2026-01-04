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



    @Override
    public int compareTo(Director other) {
        int cmp = lastName.compareTo(other.lastName);
        if (cmp == 0) {
            return firstName.compareTo(other.firstName);
        }
        return cmp;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Surname: %s | BirthYear: %d ", firstName, lastName, birthYear);
    }
}
