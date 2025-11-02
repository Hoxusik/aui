package lab.aui;

import lab.aui.entities.Director;
import lab.aui.entities.Movie;
import lab.aui.entities.MovieDTO;
import lab.aui.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Order(2)
@RequiredArgsConstructor
public class MainCommandLineRunner implements CommandLineRunner {
    private final DirectorService directorService;
    private final MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Commands: help, list-directors, list-movies, add-movie, delete-movie, exit");
        while (true) {
            System.out.print("> ");
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "help" -> System.out.println("help, list-directors, list-movies, add-movie, delete-movie, exit");
                case "list-directors" -> directorService.findAll().forEach(System.out::println);
                case "list-movies" -> movieService.findAll().forEach(System.out::println);
                case "add-movie" -> addMovieFlow(sc);
                case "delete-movie" -> deleteMovieFlow(sc);
                case "exit" -> { return; }
                default -> System.out.println("Unknown command");
            }
        }
    }

    private void addMovieFlow(Scanner sc) {
        System.out.print("Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Release year: ");
        int year = Integer.parseInt(sc.nextLine().trim());

        var directors = directorService.findAll();
        for (int i = 0; i < directors.size(); i++) {
            System.out.println((i+1) + ") " + directors.get(i).getFirstName() + " " + directors.get(i).getLastName());
        }
        System.out.print("Pick director (number): ");
        int idx = Integer.parseInt(sc.nextLine().trim()) - 1;
        var director = directors.get(idx);

        Movie movie = Movie.builder().id(UUID.randomUUID()).title(title).releaseYear(year).director(director).build();

        movieService.save(movie);
        System.out.println("Saved.");
    }

    private void deleteMovieFlow(Scanner sc) {
        var all = movieService.findAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println((i+1) + ") " + all.get(i).getTitle() + " (" + all.get(i).getId() + ")");
        }
        System.out.print("Pick movie (number): ");
        int idx = Integer.parseInt(sc.nextLine().trim()) - 1;
        UUID id = all.get(idx).getId();
        movieService.deleteById(id);
        System.out.println("Deleted.");
    }
        //Zad 1
/*        Director d1 = Director.builder()
                .firstName("Stefan")
                .lastName("Stefanko")
                .birthYear(1986)
                .build();

        Director d2 = Director.builder()
                .firstName("Maciej")
                .lastName("Komuda")
                .birthYear(2004)
                .build();

        Director d3 = Director.builder()
                .firstName("Peter")
                .lastName("Parker")
                .birthYear(1940)
                .build();


        Movie m1 = Movie.builder()
                .title("Titanic")
                .releaseYear(2010)
                .build();
        Movie m2 = Movie.builder()
                .title("My Gym")
                .releaseYear(2020)
                .build();
        Movie m3 = Movie.builder()
                .title("My School")
                .releaseYear(1970)
                .build();
        Movie m4 = Movie.builder()
                .title("My Home")
                .releaseYear(1980)
                .build();
        Movie m5 = Movie.builder()
                .title("Alabama")
                .releaseYear(2022)
                .build();
        Movie m6 = Movie.builder()
                .title("Titanic 2")
                .releaseYear(2021)
                .build();
        Movie m7 = Movie.builder()
                .title("Alabama 2")
                .releaseYear(2025)
                .build();
        Movie m8 = Movie.builder()
                .title("My Gym 2")
                .releaseYear(2023)
                .build();
        Movie m9 = Movie.builder()
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

        Collection<Director> directors = new ArrayList<>();
        directors.add(d1);
        directors.add(d2);
        directors.add(d3);*/

   /*     //Zad 2
        System.out.println("Zad 2");
        directors.forEach(director -> {
            System.out.println(director);
            director.getMovies().forEach(System.out::println);
        });

        System.out.println(" ");
        System.out.println("Zad 3");
        //Zad 3
        Set<Movie> movies = directors.stream()
                .flatMap(director -> director.getMovies().stream())
                .collect(Collectors.toSet());
        movies.forEach(System.out::println);
        System.out.println(" ");
        System.out.println("Zad 4");
        //Zad 4
        movies.stream()
                .filter(movie -> movie.getReleaseYear() > 2020)
                .sorted(Comparator.comparing(Movie::getReleaseYear))
                .forEach(System.out::println);
        System.out.println(" ");
        System.out.println("Zad 5");
        //Zad 5
        List<MovieDTO> moviesDTO = movies.stream()
                .map(movie -> MovieDTO.builder()
                        .title(movie.getTitle())
                        .releaseYear(movie.getReleaseYear())
                        .director(movie.getDirector().getLastName())
                        .build())
                .sorted()
                .toList();
        moviesDTO.forEach(System.out::println);
        System.out.println(" ");
        System.out.println("Zad 6");
        //Zad 6
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("binary.bin"));
            oos.writeObject(directors);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("binary.bin"));
            Collection<Director> directorsRead = (Collection<Director>) ois.readObject();
            directorsRead.forEach(director -> {
                System.out.println(director);
                director.getMovies().forEach(System.out::println);
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(" ");
        System.out.println("Zad 7");
        //Zad 7
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        forkJoinPool.submit(() -> {
            directors.parallelStream()
                    .forEach(director -> {
                        director.getMovies().forEach(movie -> {
                            try{
                                Thread.sleep(1000);
                                System.out.println(movie + " " + Thread.currentThread().getName());
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        });
                    });
        }).join(); //bedzie mogl sie zamknac i nie bedzie miec pustych threadow
        forkJoinPool.shutdown();*/
    }

