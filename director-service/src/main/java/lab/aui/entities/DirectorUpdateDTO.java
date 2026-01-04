package lab.aui.entities;
import jakarta.validation.constraints.Positive;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Comparator;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DirectorUpdateDTO implements Comparable<DirectorUpdateDTO>{
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @NotBlank @Positive Integer birthYear;

    @Override
    public int compareTo(DirectorUpdateDTO other) {
        int cmp = lastName.compareTo(other.lastName);
        if (cmp == 0) {
            return firstName.compareTo(other.firstName);
        }
        return cmp;
    }
}
