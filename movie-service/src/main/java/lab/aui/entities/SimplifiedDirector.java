package lab.aui.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // DODAJ TO
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "simplified_directors")
@JsonIgnoreProperties(ignoreUnknown = true) // KLUCZOWE: Ignoruje birthYear przychodzący z requestu
public class SimplifiedDirector implements Serializable {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}