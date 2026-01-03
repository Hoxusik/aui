package lab.aui.repositories;

import lab.aui.entities.Director;
import lab.aui.entities.DirectorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DirectorEventRepository {
    private final RestTemplate restTemplate;

    public void delete(UUID id) {
        // Wysyłamy żądanie DELETE do movie-service
        restTemplate.delete("/api/directors/{id}", id);
    }

    public void upsert(Director director) {
        // Konwertujemy encję na DTO, żeby wysłać czyste dane
        DirectorDTO payload = DirectorDTO.from(director);

        // Wysyłamy PUT na adres /api/directors/{id}
        // Dzięki rootUri w AuiApplication, wystarczy podać ścieżkę względną
        restTemplate.put("/api/directors/" + director.getId(), payload);
    }
}