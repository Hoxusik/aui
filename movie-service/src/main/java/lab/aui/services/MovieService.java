package lab.aui.services;

import lab.aui.entities.Movie;
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
}
