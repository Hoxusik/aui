import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule, Router } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { Director } from '../../models/director';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-director-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './director-details.html',
  styleUrl: './director-details.scss',
})
export class DirectorDetailsComponent implements OnInit {
  director: Director | undefined;
  movies: Movie[] = [];

  constructor(private api: ApiService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.fetchData(id);
    }
  }

  fetchData(id: string): void {
    // 1. Pobierz reżysera
    this.api.getDirector(id).subscribe({
      next: (data) => (this.director = data),
      error: (err) => console.error('Błąd pobierania reżysera:', err),
    });

    // 2. Pobierz filmy (z debugowaniem w konsoli)
    this.api.getMovies().subscribe({
      next: (allMovies) => {
        console.log('--- DEBUGOWANIE ---');
        console.log('Szukam filmów dla reżysera o ID:', id);
        console.log('Wszystkie filmy z API:', allMovies);

        // filtr
        this.movies = allMovies.filter((movie) => movie.directorId === id);

        console.log('Filmy po przefiltrowaniu:', this.movies);
        console.log('-------------------');
      },
      error: (err) => console.error('Błąd pobierania filmów:', err),
    });
  }

  deleteMovie(movieId: string): void {
    if (confirm('Czy na pewno chcesz usunąć ten film?')) {
      this.api.deleteMovie(movieId).subscribe({
        next: () => {
          if (this.director) {
            this.fetchData(this.director.id);
          }
        },
        error: (err) => console.error('Błąd usuwania filmu:', err),
      });
    }
  }
}
