import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Potrzebne do formularzy
import { Router, RouterModule } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { Director } from '../../models/director';

@Component({
  selector: 'app-director-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule], // Dodajemy FormsModule
  templateUrl: './director-form.html',
  styleUrl: './director-form.scss',
})
export class DirectorFormComponent {
  // Pusty obiekt reżysera, który będziemy wypełniać
  director: Partial<Director> = {
    firstName: '',
    lastName: '',
    birthYear: 1980,
  };

  constructor(private api: ApiService, private router: Router) {}

  onSubmit(): void {
    // Wysyłamy dane do Gatewaya
    this.api.createDirector(this.director).subscribe({
      next: () => {
        // Jak się uda, wracamy na listę
        this.router.navigate(['/directors']);
      },
      error: (err) => console.error('Błąd zapisu:', err),
    });
  }
}
