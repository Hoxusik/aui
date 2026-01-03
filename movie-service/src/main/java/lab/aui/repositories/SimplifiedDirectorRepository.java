package lab.aui.repositories;

import lab.aui.entities.SimplifiedDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SimplifiedDirectorRepository extends JpaRepository<SimplifiedDirector, UUID> {
}