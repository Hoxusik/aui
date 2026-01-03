import { Routes } from '@angular/router';
import { DirectorListComponent } from './components/director-list/director-list';

export const routes: Routes = [
  { path: 'directors', component: DirectorListComponent },

  { path: '', redirectTo: 'directors', pathMatch: 'full' },
];
