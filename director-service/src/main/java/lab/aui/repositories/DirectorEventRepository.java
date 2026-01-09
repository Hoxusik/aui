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
        restTemplate.delete("/api/directors/{id}", id);
    }

    public void upsert(Director director) {
        DirectorDTO payload = DirectorDTO.from(director);

        restTemplate.put("/api/directors/" + director.getId(), payload);
    }
}