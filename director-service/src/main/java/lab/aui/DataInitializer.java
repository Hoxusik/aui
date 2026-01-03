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
    //private final MovieService movieService;
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


        directorService.save(d1); // dzięki cascade = ALL zapisze też filmy
        directorService.save(d2);
        directorService.save(d3);
    }
    }

