package lab.aui.services;

import lab.aui.entities.Director;
import lab.aui.repositories.DirectorRepository;
import lab.aui.repositories.DirectorEventRepository; // Importujemy nasze nowe repo
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorEventRepository directorEventRepository; // Wstrzykujemy

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public Optional<Director> findById(UUID id) {
        return directorRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return directorRepository.existsById(id);
    }

    // Metoda create używana przez DataInitializer i Controller
    @Transactional
    public Director create(String firstName, String lastName, int birthYear) {
        var d = Director.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .birthYear(birthYear)
                .build();
        Director saved = directorRepository.save(d);

        // POWIADOMIENIE (Event)
        directorEventRepository.upsert(saved);

        return saved;
    }

    @Transactional
    public Director save(Director director) {
        if (director.getId() == null) {
            director.setId(UUID.randomUUID());
        }
        Director saved = directorRepository.save(director);

        // POWIADOMIENIE (Event)
        directorEventRepository.upsert(saved);

        return saved;
    }

    @Transactional
    public Director update(UUID id, String firstName, String lastName, int birthYear) {
        var d = directorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Director not found: " + id));
        d.setFirstName(firstName);
        d.setLastName(lastName);
        d.setBirthYear(birthYear);

        Director updated = directorRepository.save(d);

        // POWIADOMIENIE (Event)
        directorEventRepository.upsert(updated);

        return updated;
    }

    @Transactional
    public void deleteById(UUID id) {
        directorRepository.deleteById(id);

        // POWIADOMIENIE (Event)
        directorEventRepository.delete(id);
    }
}