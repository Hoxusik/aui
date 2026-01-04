package lab.aui.resources;

import lab.aui.entities.SimplifiedDirector;
import lab.aui.repositories.SimplifiedDirectorRepository;
import lab.aui.repositories.MovieRepository;
import lab.aui.entities.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/directors") // Musi pasować do ścieżki wysyłanej przez director-service
@RequiredArgsConstructor
public class SimplifiedDirectorResource {

    private final SimplifiedDirectorRepository simplifiedDirectorRepository;
    private final MovieRepository movieRepository;

    // Odbieramy PUT (Utwórz lub Aktualizuj)
    @PutMapping("/{id}")
    public ResponseEntity<Void> createOrUpdateDirector(@PathVariable UUID id, @RequestBody SimplifiedDirector director) {
        // Upewniamy się, że ID jest spójne
        if (!id.equals(director.getId())) {
            director.setId(id);
        }
        simplifiedDirectorRepository.save(director);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Odbieramy DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteDirector(@PathVariable UUID id) {
        // 1. Usuwamy filmy tego reżysera (kaskadowość manualna)
        List<Movie> movies = movieRepository.findByDirectorId(id);
        movieRepository.deleteAll(movies);

        // 2. Usuwamy reżysera
        if (simplifiedDirectorRepository.existsById(id)) {
            simplifiedDirectorRepository.deleteById(id);
        }

        return ResponseEntity.noContent().build();
    }
}