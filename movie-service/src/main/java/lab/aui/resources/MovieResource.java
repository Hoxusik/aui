package lab.aui.resources;

import lab.aui.entities.*;
import lab.aui.services.MovieService;
import lab.aui.repositories.SimplifiedDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
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
                        .id(m.getId()) // <--- WAŻNE: Musisz zwracać ID filmu
                        .title(m.getTitle())
                        .releaseYear(m.getReleaseYear())
                        .directorId(m.getDirector().getId()) // <--- KLUCZOWE: ID reżysera dla Angulara!
                        .director(m.getDirector().toString())
                        .build())
                .toList();
    }

    // TĘ FUNKCJĘ NAPRAWIAMY:
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable UUID id) {
        return movieService.findById(id)
                .map(m -> MovieDTO.builder()
                        .id(m.getId())
                        .title(m.getTitle())
                        .releaseYear(m.getReleaseYear())
                        .directorId(m.getDirector().getId()) // Tu też dodajemy ID reżysera
                        .director(m.getDirector().toString())
                        .build())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
                        .id(movie.getId()) // Tu też zwracamy ID
                        .title(movie.getTitle())
                        .releaseYear(movie.getReleaseYear())
                        .directorId(director.getId())
                        .director(director.toString())
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteById(id); // Upewnij się, że masz deleteById w serwisie
        return ResponseEntity.noContent().build();
    }
}