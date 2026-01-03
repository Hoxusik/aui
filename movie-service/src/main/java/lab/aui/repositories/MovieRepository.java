package lab.aui.repositories;

import lab.aui.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    List<Movie> findByDirectorId(UUID directorId);
    List<Movie> findByDirectorLastName(String lastName);
}
