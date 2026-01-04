import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './movie-details.html',
  styleUrl: './movie-details.scss',
})
export class MovieDetailsComponent implements OnInit {
  movie: Movie | undefined;
  directorId: string | null = null;

  constructor(private api: ApiService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Pobieramy ID filmu i ID reżysera (potrzebne do przycisku "Wstecz")
    this.directorId = this.route.snapshot.paramMap.get('directorId');
    const movieId = this.route.snapshot.paramMap.get('movieId');

    if (movieId) {
      this.api.getMovie(movieId).subscribe({
        next: (data) => (this.movie = data),
        error: (err) => console.error('Błąd pobierania filmu:', err),
      });
    }
  }
}
