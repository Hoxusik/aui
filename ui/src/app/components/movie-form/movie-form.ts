import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movie-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './movie-form.html',
  styleUrl: './movie-form.scss',
})
export class MovieFormComponent implements OnInit {
  movie: Partial<Movie> = {
    title: '',
    releaseYear: 2024,
    directorId: '',
  };

  directorId: string | null = null;
  movieId: string | null = null;
  isEditMode = false;

  constructor(private api: ApiService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.directorId = this.route.snapshot.paramMap.get('directorId');
    this.movieId = this.route.snapshot.paramMap.get('movieId');

    if (this.directorId) {
      this.movie.directorId = this.directorId;
    }

    if (this.movieId) {
      this.isEditMode = true;
      this.loadMovie(this.movieId);
    }
  }

  loadMovie(id: string): void {
    this.api.getMovie(id).subscribe({
      next: (data) => (this.movie = data),
      error: (err) => console.error('Błąd:', err),
    });
  }

  onSubmit(): void {
    if (this.isEditMode && this.movieId) {
      this.api.updateMovie(this.movieId, this.movie).subscribe({
        next: () => this.goBack(),
        error: (err) => console.error('Błąd edycji:', err),
      });
    } else {
      this.api.createMovie(this.movie).subscribe({
        next: () => this.goBack(),
        error: (err) => console.error('Błąd tworzenia:', err),
      });
    }
  }

  goBack(): void {
    this.router.navigate(['/directors', this.directorId]);
  }
}
