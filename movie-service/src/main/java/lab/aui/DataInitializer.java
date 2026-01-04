package lab.aui;

import lab.aui.entities.*;
import lab.aui.repositories.SimplifiedDirectorRepository;
import lab.aui.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@Order(1)
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SimplifiedDirectorRepository simplifiedDirectorRepository;
    private final MovieService movieService;

    @Override
    public void run(String... args) {
        UUID id1 = UUID.fromString("c4804e0f-769e-470b-9e22-953801d9f2c9");
        UUID id2 = UUID.fromString("6b834925-06c2-4210-917b-d0e883a9366b");
        UUID id3 = UUID.fromString("e5352c21-d85c-42b7-8d07-6b45d2e08611");

        // tworzenie simplifiedrdirectors
        SimplifiedDirector sd1 = SimplifiedDirector.builder()
                .id(id1)
                .firstName("Stefan")
                .lastName("Stefanko")
                .build();

        SimplifiedDirector sd2 = SimplifiedDirector.builder()
                .id(id2)
                .firstName("Michał")
                .lastName("Siembida")
                .build();

        SimplifiedDirector sd3 = SimplifiedDirector.builder()
                .id(id3)
                .firstName("Peter")
                .lastName("Parker")
                .build();

        simplifiedDirectorRepository.save(sd1);
        simplifiedDirectorRepository.save(sd2);
        simplifiedDirectorRepository.save(sd3);

        // tworzenie fimow
        movieService.save(Movie.builder().id(UUID.randomUUID()).title("Titanic").releaseYear(2010).director(sd1).build());
        movieService.save(Movie.builder().id(UUID.randomUUID()).title("My Home").releaseYear(1980).director(sd1).build());

        movieService.save(Movie.builder().id(UUID.randomUUID()).title("My Gym").releaseYear(2020).director(sd2).build());
        movieService.save(Movie.builder().id(UUID.randomUUID()).title("Alabama").releaseYear(2022).director(sd2).build());
        movieService.save(Movie.builder().id(UUID.randomUUID()).title("Titanic 2").releaseYear(2021).director(sd2).build());
        movieService.save(Movie.builder().id(UUID.randomUUID()).title("My Home 2").releaseYear(2000).director(sd2).build());

        movieService.save(Movie.builder().id(UUID.randomUUID()).title("My School").releaseYear(1970).director(sd3).build());
        movieService.save(Movie.builder().id(UUID.randomUUID()).title("Alabama 2").releaseYear(2025).director(sd3).build());
        movieService.save(Movie.builder().id(UUID.randomUUID()).title("My Gym 2").releaseYear(2023).director(sd3).build());
    }
}