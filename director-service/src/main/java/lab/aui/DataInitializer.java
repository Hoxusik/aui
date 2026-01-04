package lab.aui;

import lab.aui.entities.Director;
import lab.aui.services.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final DirectorService directorService;

    @Override
    public void run(String... args) {
        // TE SAME UUID CO W MOVIE-SERVICE
        UUID id1 = UUID.fromString("c4804e0f-769e-470b-9e22-953801d9f2c9");
        UUID id2 = UUID.fromString("6b834925-06c2-4210-917b-d0e883a9366b");
        UUID id3 = UUID.fromString("e5352c21-d85c-42b7-8d07-6b45d2e08611");

        Director d1 = Director.builder()
                .id(id1)
                .firstName("Stefan")
                .lastName("Stefanko")
                .birthYear(1980)
                .build();

        Director d2 = Director.builder()
                .id(id2)
                .firstName("Michał")
                .lastName("Siembida")
                .birthYear(2004)
                .build();

        Director d3 = Director.builder()
                .id(id3)
                .firstName("Peter")
                .lastName("Parker")
                .birthYear(1975)
                .build();

        directorService.save(d1);
        directorService.save(d2);
        directorService.save(d3);
    }
}