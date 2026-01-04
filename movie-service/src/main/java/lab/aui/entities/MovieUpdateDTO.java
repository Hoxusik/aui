package lab.aui.entities;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdateDTO {
    private String title;
    private int releaseYear;
    private UUID directorId; // Kluczowe: ID reżysera, do którego przypisujemy film
}