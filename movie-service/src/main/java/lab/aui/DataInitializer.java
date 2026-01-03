package lab.aui;

import lab.aui.entities.*;
import lab.aui.repositories.SimplifiedDirectorRepository;
import java.util.ArrayList;
import java.util.Collection;

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
            public void run(String... args)
    {
        SimplifiedDirector sd1 = SimplifiedDirector.builder()
                .id(UUID.randomUUID())
                .firstName("Stefan")
                .lastName("Stefanko")
                .build();

        SimplifiedDirector sd2 = SimplifiedDirector.builder()
                .id(UUID.randomUUID())
                .firstName("Maciej")
                .lastName("Komuda")
                .build();

        SimplifiedDirector sd3 = SimplifiedDirector.builder()
                .id(UUID.randomUUID())
                .firstName("Peter")
                .lastName("Parker")
                .build();

        simplifiedDirectorRepository.save(sd1);
        simplifiedDirectorRepository.save(sd2);
        simplifiedDirectorRepository.save(sd3);

        Movie m1 = Movie.builder()
                .id(UUID.randomUUID())
                .title("Titanic")
                .releaseYear(2010)
                .director(sd1)
                .build();
        Movie m2 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My Gym")
                .releaseYear(2020)
                .director(sd2)
                .build();
        Movie m3 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My School")
                .releaseYear(1970)
                .director(sd3)
                .build();
        Movie m4 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My Home")
                .releaseYear(1980)
                .director(sd1)
                .build();
        Movie m5 = Movie.builder()
                .id(UUID.randomUUID())
                .title("Alabama")
                .releaseYear(2022)
                .director(sd2)
                .build();
        Movie m6 = Movie.builder()
                .id(UUID.randomUUID())
                .title("Titanic 2")
                .releaseYear(2021)
                .director(sd2)
                .build();
        Movie m7 = Movie.builder()
                .id(UUID.randomUUID())
                .title("Alabama 2")
                .releaseYear(2025)
                .director(sd3)
                .build();
        Movie m8 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My Gym 2")
                .releaseYear(2023)
                .director(sd3)
                .build();
        Movie m9 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My Home 2")
                .releaseYear(2000)
                .director(sd2)
                .build();

        movieService.save(m1);
        movieService.save(m2);
        movieService.save(m3);
        movieService.save(m4);
        movieService.save(m5);
        movieService.save(m6);
        movieService.save(m7);
        movieService.save(m8);
        movieService.save(m9);

    }
    }

