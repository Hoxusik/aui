package lab.aui.entities;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DirectorDTO implements Comparable<DirectorDTO> {
    private String id;
    private String firstName;
    private String lastName;
    private String birthYear;

    public static DirectorDTO from(Director director) {
        return DirectorDTO.builder()
                .id(director.getId().toString())
                .firstName(director.getFirstName())
                .lastName(director.getLastName())
                .birthYear(director.getBirthYear().toString())
                .build();
    }
    @Override
    public int compareTo(DirectorDTO other) {
        int cmp = lastName.compareTo(other.lastName);
        if (cmp == 0) {
            return firstName.compareTo(other.firstName);
        }
        return cmp;
    }
}
