
/*import lab.aui.entities.Movie;
import lab.aui.repositories.MovieRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
    public List<Movie> findByDirectorId(UUID directorId) {
        return movieRepository.findByDirectorId(directorId);
    }
    public Movie save(Movie m) {
        return movieRepository.save(m);
    }
    public void deleteById(UUID id) {
        movieRepository.deleteById(id);
    }
}*/
package lab.aui.services;

import lab.aui.entities.Director;
import lab.aui.entities.Movie;
import lab.aui.repositories.DirectorRepository;
import lab.aui.repositories.MovieRepository;
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
public class MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(UUID id) {
        return movieRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return movieRepository.existsById(id);
    }

    public long count() {
        return movieRepository.count();
    }

    public List<Movie> findByDirectorId(UUID directorId) {
        return movieRepository.findByDirectorId(directorId);
    }

    @Transactional
    public Movie create(UUID directorId, String title, int releaseYear) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new NoSuchElementException("Director not found: " + directorId));

        Movie m = Movie.builder()
                .id(UUID.randomUUID()) // UUID po stronie klienta
                .title(title)
                .releaseYear(releaseYear)
                .director(director)
                .build();

        // director.addMovie(m); // i wtedy save po stronie directorRepository
        return movieRepository.save(m);
    }

    @Transactional
    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            movie.setId(UUID.randomUUID());
        }
        if (movie.getDirector() == null) {
            throw new IllegalArgumentException("Movie must have a director assigned");
        }
        return movieRepository.save(movie);
    }

    @Transactional
    public Movie update(UUID id, String title, int releaseYear) {
        var m = movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movie not found: " + id));
        m.setTitle(title);
        m.setReleaseYear(releaseYear);
        return movieRepository.save(m);
    }

    @Transactional
    public List<Movie> saveAll(Iterable<Movie> movies) {
        for (Movie m : movies) {
            if (m.getId() == null) m.setId(UUID.randomUUID());
            if (m.getDirector() == null)
                throw new IllegalArgumentException("Movie must have a director assigned: " + m);
        }
        return movieRepository.saveAll(movies);
    }

    @Transactional
    public void deleteById(UUID id) {
        movieRepository.deleteById(id);
    }

    @Transactional
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }
}
