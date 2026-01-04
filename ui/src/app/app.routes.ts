import { Routes } from '@angular/router';
import { DirectorListComponent } from './components/director-list/director-list';
import { DirectorFormComponent } from './components/director-form/director-form';
import { DirectorDetailsComponent } from './components/director-details/director-details';
import { MovieDetailsComponent } from './components/movie-details/movie-details';
import { MovieFormComponent } from './components/movie-form/movie-form';

export const routes: Routes = [
  // --- REŻYSERZY ---
  { path: 'directors', component: DirectorListComponent },
  { path: 'directors/new', component: DirectorFormComponent },
  { path: 'directors/:id/edit', component: DirectorFormComponent },

  { path: 'directors/:directorId/movies/new', component: MovieFormComponent },
  { path: 'directors/:directorId/movies/:movieId/edit', component: MovieFormComponent },

  { path: 'directors/:directorId/movies/:movieId', component: MovieDetailsComponent },

  { path: 'directors/:id', component: DirectorDetailsComponent },

  { path: '', redirectTo: 'directors', pathMatch: 'full' },
];
