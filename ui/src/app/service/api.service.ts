import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Director } from '../models/director';
import { Movie } from '../models/movie';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  // Dzięki proxy.conf.json, '/api' zostanie przekierowane na 'localhost:8080'
  private apiUrl = '/api';

  constructor(private http: HttpClient) {}

  /* --- REŻYSERZY (CATEGORIES) --- */
  getDirectors(): Observable<Director[]> {
    return this.http.get<Director[]>(`${this.apiUrl}/directors`);
  }

  getDirector(id: string): Observable<Director> {
    return this.http.get<Director>(`${this.apiUrl}/directors/${id}`);
  }

  createDirector(director: any): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/directors`, director);
  }

  deleteDirector(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/directors/${id}`);
  }

  updateDirector(id: string, director: any): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/directors/${id}`, director);
  }

  /* --- FILMY (ELEMENTS) --- */
  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.apiUrl}/movies`);
  }

  getMovie(id: string): Observable<Movie> {
    return this.http.get<Movie>(`${this.apiUrl}/movies/${id}`);
  }

  createMovie(movie: any): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/movies`, movie);
  }

  updateMovie(id: string, movie: any): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/movies/${id}`, movie);
  }

  deleteMovie(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/movies/${id}`);
  }
}
