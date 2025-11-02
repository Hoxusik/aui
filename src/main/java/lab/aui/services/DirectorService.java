package lab.aui.services;
import lab.aui.entities.Director;
import lab.aui.repositories.DirectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    public List<Director> findAll() {
        return directorRepository.findAll();
    }
    //public Optional<Director> findById(UUID id) {
    //    return directorRepository.findById(id);
    //}
    public Director save(Director d) {
        return directorRepository.save(d);
    }
    //public void deleteById(UUID id) {
    //    directorRepository.deleteById(id);
    //}
}
