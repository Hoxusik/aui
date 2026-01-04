import { Routes } from '@angular/router';
import { DirectorListComponent } from './components/director-list/director-list';
import { DirectorFormComponent } from './components/director-form/director-form';
import { DirectorDetailsComponent } from './components/director-details/director-details';

export const routes: Routes = [
  { path: 'directors', component: DirectorListComponent },
  { path: 'directors/new', component: DirectorFormComponent }, // dodanie nowego
  { path: 'directors/:id/edit', component: DirectorFormComponent }, // edycja istniejacego
  { path: 'directors/:id', component: DirectorDetailsComponent }, //szczegoly
  { path: '', redirectTo: 'directors', pathMatch: 'full' },
];
