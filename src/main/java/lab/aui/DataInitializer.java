package lab.aui;

import lab.aui.entities.*;
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
    private final DirectorService directorService;
    private final MovieService movieService;
    @Override
            public void run(String... args)
    {
        Director d1 = Director.builder()
                .id(UUID.randomUUID())
                .firstName("Stefan")
                .lastName("Stefanko")
                .birthYear(1986)
                .build();

        Director d2 = Director.builder()
                .id(UUID.randomUUID())
                .firstName("Maciej")
                .lastName("Komuda")
                .birthYear(2004)
                .build();

        Director d3 = Director.builder()
                .id(UUID.randomUUID())
                .firstName("Peter")
                .lastName("Parker")
                .birthYear(1940)
                .build();


        Movie m1 = Movie.builder()
                .id(UUID.randomUUID())
                .title("Titanic")
                .releaseYear(2010)
                .build();
        Movie m2 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My Gym")
                .releaseYear(2020)
                .build();
        Movie m3 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My School")
                .releaseYear(1970)
                .build();
        Movie m4 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My Home")
                .releaseYear(1980)
                .build();
        Movie m5 = Movie.builder()
                .id(UUID.randomUUID())
                .title("Alabama")
                .releaseYear(2022)
                .build();
        Movie m6 = Movie.builder()
                .id(UUID.randomUUID())
                .title("Titanic 2")
                .releaseYear(2021)
                .build();
        Movie m7 = Movie.builder()
                .id(UUID.randomUUID())
                .title("Alabama 2")
                .releaseYear(2025)
                .build();
        Movie m8 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My Gym 2")
                .releaseYear(2023)
                .build();
        Movie m9 = Movie.builder()
                .id(UUID.randomUUID())
                .title("My Home 2")
                .releaseYear(2000)
                .build();

        d1.addMovie(m6);
        d1.addMovie(m9);
        d1.addMovie(m1);
        d2.addMovie(m2);
        d2.addMovie(m5);
        d2.addMovie(m7);
        d3.addMovie(m3);
        d3.addMovie(m4);
        d3.addMovie(m8);

        directorService.save(d1); // dzięki cascade = ALL zapisze też filmy
        directorService.save(d2);
        directorService.save(d3);
    }
    }

