package lab.aui.resources;

import lab.aui.entities.*;
import lab.aui.services.MovieService;
import lab.aui.repositories.SimplifiedDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/movies")
@RequiredArgsConstructor
public class MovieResource {
    private final MovieService movieService;
    private final SimplifiedDirectorRepository simplifiedDirectorRepository;

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieService.findAll().stream()
                .map(m -> MovieDTO.builder()
                        .title(m.getTitle())
                        .releaseYear(m.getReleaseYear())
                        .director(m.getDirector().toString()) // Używamy toString z SimplifiedDirector
                        .build())
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable UUID id) {
        // Tu warto dodać metodę findById w serwisie, ale na razie użyjmy repo w serwisie lub dopiszmy logikę
        // Zakładam, że w serwisie dodasz metodę findById, jeśli jej nie ma:
        return ResponseEntity.notFound().build(); // Placeholder, dopisz w serwisie findById
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieUpdateDTO dto) {
        // 1. Szukamy uproszczonego reżysera w LOKALNEJ bazie movie-service
        SimplifiedDirector director = simplifiedDirectorRepository.findById(dto.getDirectorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Director not found in local DB"));

        // 2. Tworzymy film
        Movie movie = Movie.builder()
                .id(UUID.randomUUID())
                .title(dto.getTitle())
                .releaseYear(dto.getReleaseYear())
                .director(director)
                .build();

        movieService.save(movie);

        return ResponseEntity.created(URI.create("/api/movies/" + movie.getId()))
                .body(MovieDTO.builder()
                        .title(movie.getTitle())
                        .releaseYear(movie.getReleaseYear())
                        .director(director.toString())
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}