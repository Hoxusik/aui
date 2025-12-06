package lab.aui.services;

import lab.aui.entities.Director;
import lab.aui.repositories.DirectorRepository;
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

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public Optional<Director> findById(UUID id) {
        return directorRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return directorRepository.existsById(id);
    }

    public long count() {
        return directorRepository.count();
    }

    @Transactional
    public Director create(String firstName, String lastName, int birthYear) {
        var d = Director.builder()
                .id(UUID.randomUUID()) // UUID generowany po stronie klienta
                .firstName(firstName)
                .lastName(lastName)
                .birthYear(birthYear)
                .build();
        return directorRepository.save(d);
    }

    @Transactional
    public Director save(Director director) {
        // Zakładamy, że ID jest ustawione w encji; jeśli nie, nadaj je:
        if (director.getId() == null) {
            director.setId(UUID.randomUUID());
        }
        return directorRepository.save(director);
    }

    @Transactional
    public Director update(UUID id, String firstName, String lastName, int birthYear) {
        var d = directorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Director not found: " + id));
        d.setFirstName(firstName);
        d.setLastName(lastName);
        d.setBirthYear(birthYear);
        return directorRepository.save(d);
    }

    @Transactional
    public List<Director> saveAll(Iterable<Director> directors) {
        for (Director d : directors) {
            if (d.getId() == null) d.setId(UUID.randomUUID());
        }
        return directorRepository.saveAll(directors);
    }

    @Transactional
    public void deleteById(UUID id) {
        directorRepository.deleteById(id); // cascade + orphanRemoval w Director usunie również filmy
    }

    @Transactional
    public void delete(Director director) {
        directorRepository.delete(director);
    }
}
