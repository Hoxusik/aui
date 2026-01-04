import { Routes } from '@angular/router';
import { DirectorListComponent } from './components/director-list/director-list';
// Importujemy nowy komponent (zwróć uwagę na nazwę pliku, u Ciebie może być bez .component)
import { DirectorFormComponent } from './components/director-form/director-form';

export const routes: Routes = [
  { path: 'directors', component: DirectorListComponent },
  { path: 'directors/new', component: DirectorFormComponent }, // <--- NOWA TRASA
  { path: '', redirectTo: 'directors', pathMatch: 'full' },
];
